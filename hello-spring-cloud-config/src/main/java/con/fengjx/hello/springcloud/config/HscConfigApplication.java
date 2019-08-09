package con.fengjx.hello.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author fengjianxin
 */
@EnableConfigServer
@SpringBootApplication
public class HscConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HscConfigApplication.class, args);
    }

}
