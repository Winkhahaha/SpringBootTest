package com.example.springbootimportant.start;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my.hello")
@Data
public class HelloProperties {

    private String prefix;
    private String suffix;
}
