package com.example.demo;

import com.example.demo.configure.FooProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * this code is investigation.
 */
@Component
public class MyAppConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "my-app.my-module.foo", name = "min-height", havingValue = "100")
    //@ConditionalOnProperty(prefix = "my-app.my-module.foo", name = "minHeight")
    public BarProperties foo(FooProperties foo) {
        System.out.println("inject foo : " + foo.toString());
        return BarProperties.builder().name(foo.getName()).build();
    }

    @Data
    @Builder
    public static class BarProperties {
        private String name;
    }

}
