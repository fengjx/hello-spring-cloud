package com.fengjx.hello.springcloud.commons.apm.accesslog;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gzfengjianxin
 * @version 2019-04-12.
 */
@Getter
@Setter
@Builder
public class Accesslog {

    private String domain;
    private String localtime;
    private long timestamp;
    private String method;
    private String uri;
    private int httpcode;
    private String querystring;
    private String protocol;
    private String host;
    private String ip;
    private String xff;
    private int bytes;
    private String referer;
    private String useragent;
    private long responsetime;
    private String user;
    private String traceId;
    private String spanId;
    private String xB3TraceId;
    private String xB3SpanId;


}
