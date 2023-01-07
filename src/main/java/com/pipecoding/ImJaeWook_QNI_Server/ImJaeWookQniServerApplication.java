package com.pipecoding.ImJaeWook_QNI_Server;

import com.pipecoding.ImJaeWook_QNI_Server.util.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class ImJaeWookQniServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImJaeWookQniServerApplication.class, args);
	}

}
