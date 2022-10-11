package org.my.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "gateway")
public class NacosTestConfig {
    /**
     * 默认本工程共享配置(dev、test、prod等共享) [spring.application.name].yaml --> springcloud-gateway.yaml
     *
     * 共享配置,可用于所有项目全局配置 - user.pwd
     */
    @Value("${name:lisiapp}")
    private String name;

    @Value("${pwd:123456}")
    private String pwd;

}
