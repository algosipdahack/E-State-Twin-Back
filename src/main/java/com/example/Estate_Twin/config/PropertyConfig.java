package com.example.Estate_Twin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("clalsspath:properties/env.properties")
})
public class PropertyConfig {
}
