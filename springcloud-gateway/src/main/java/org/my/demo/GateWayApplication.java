package org.my.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GateWayApplication.class);
        // TODO 测试nacos更新gateway.name值，本地是否更新
        // while (true) {
        // String name = applicationContext.getEnvironment().getProperty("gateway.name");
        // log.info("*********** get nacos config value， gateway.name: {}", name);
        // Thread.sleep(3000L);
        // }
    }

}
