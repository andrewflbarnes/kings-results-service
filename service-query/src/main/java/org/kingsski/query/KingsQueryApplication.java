package org.kingsski.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:database.yaml", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:database.yml", ignoreResourceNotFound = true)
})
public class KingsQueryApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KingsQueryApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(KingsQueryApplication.class, args);
    }

}
