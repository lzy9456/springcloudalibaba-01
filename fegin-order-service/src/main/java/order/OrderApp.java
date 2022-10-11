package order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApp {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderApp.class);
        nacosConfigValuePrint(applicationContext);
    }

    private static void nacosConfigValuePrint(ConfigurableApplicationContext applicationContext) throws InterruptedException {
        while (true) {
            String name = applicationContext.getEnvironment().getProperty("order.key");
            log.info("*********** get nacos config value， order.key: {}", name);
            Thread.sleep(3000L);
        }
    }
}
