package com.fengjx.hello.springcloud.commons.apm.accesslog;

import com.fengjx.hello.springcloud.commons.utils.IpUtils;
import com.fengjx.hello.springcloud.commons.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author fengjianxin
 */
@Slf4j
public class AccessLoggingHttpTraceRepositoryDecorator implements HttpTraceRepository {

    private HttpTraceRepository delegate;
    private Logger logger;

    public AccessLoggingHttpTraceRepositoryDecorator(HttpTraceRepository delegate, Logger logger) {
        this.delegate = delegate;
        this.logger = logger;
    }

    @Override
    public List<HttpTrace> findAll() {
        return delegate.findAll();
    }

    @Override
    public void add(HttpTrace trace) {
        if (log.isInfoEnabled()) {
            try {
                log(trace);
            } catch (Exception e) {
                log.error("Failed to log trace " + trace, e);
            }
        }
        delegate.add(trace);
    }

    private void log(HttpTrace trace) {
        log.debug("accesslog debug: {}", JsonUtil.toJson(trace));
        HttpTrace.Request request = trace.getRequest();
        HttpTrace.Response response = trace.getResponse();
        String traceId = MDC.get("traceId");
        String spanId = MDC.get("spanId");
        String xB3SpanId = MDC.get("X-B3-SpanId");
        String xB3TraceId = MDC.get("X-B3-TraceId");
        String host = IpUtils.getLocalInnerIp();

        List<String> hostList = request.getHeaders().get(HttpHeaders.HOST);
        String domain = CollectionUtils.isEmpty(hostList) ? request.getUri().getHost() : hostList.get(0);

        String queryString = StringUtils.defaultString(request.getUri().getQuery(), "");
        String protocol = "";
        try {
            protocol = request.getUri().toURL().getProtocol();
        } catch (MalformedURLException e) {
            log.error("get protocol error", e);
        }
        List<String> refererList = request.getHeaders().get("referer");
        String referer = CollectionUtils.isEmpty(refererList) ? "" : refererList.get(0);
        List<String> uaList = request.getHeaders().get(HttpHeaders.USER_AGENT);
        String ua = CollectionUtils.isEmpty(uaList) ? "" : uaList.get(0);

        String method = request.getMethod();
        String path = request.getUri().getPath();
        String ip = StringUtils.removeStart(request.getRemoteAddress(), "/");
        List<String> ipList = request.getHeaders().get("X-Real-IP");
        if (!CollectionUtils.isEmpty(ipList)) {
            ip = ipList.get(0);
        }

        List<String> xffList = request.getHeaders().get("X-Forwarded-For");
        String xff = CollectionUtils.isEmpty(xffList) ? "" : xffList.get(0);

        Long timeTaken = trace.getTimeTaken();
        String localtime = trace.getTimestamp().toString();
        long timestamp = trace.getTimestamp().toEpochMilli();
        int httpcode = response.getStatus();
        int bytes = 0;
        List<String> lenList = response.getHeaders().get(HttpHeaders.CONTENT_LENGTH);
        if (!CollectionUtils.isEmpty(lenList)) {
            bytes = Integer.parseInt(lenList.get(0));
        }

        Accesslog log = Accesslog.builder().domain(domain).localtime(localtime).timestamp(timestamp).method(method).uri(path)
                .httpcode(httpcode).querystring(queryString).protocol(protocol).ip(ip).bytes(bytes).referer(referer)
                .useragent(ua).xff(xff).host(host).responsetime(timeTaken).traceId(traceId).spanId(spanId)
                .xB3TraceId(xB3TraceId).xB3SpanId(xB3SpanId).build();

        logger.info(JsonUtil.toJson(log));

    }

}
