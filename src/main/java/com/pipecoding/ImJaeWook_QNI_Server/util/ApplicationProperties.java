package com.pipecoding.ImJaeWook_QNI_Server.util;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@ConfigurationProperties(prefix = "security")
public class ApplicationProperties {

    private final List<String> whiteListURLs;

    public ApplicationProperties(List<String> whiteListURLs) {
        this.whiteListURLs = whiteListURLs;
    }
}