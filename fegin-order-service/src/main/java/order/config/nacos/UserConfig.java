package order.config.nacos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Component 配置类注解(使用@Configuration，nacos2.0.3出现不能动态刷新属性的情况，改为使用@Component)
 * @ConfigurationProperties 此类配置前缀，如类中的属性都在此前缀下（order.key, order.age）
 * @RefreshScope 动态刷新
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "order")
public class UserConfig {

    @Value("${key:100001}")
    private String  key;

    @Value("${age:0}")
    private Integer age;

    @Value("${sex:0}")
    private Integer sex;

}
