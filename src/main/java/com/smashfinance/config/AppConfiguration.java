package com.smashfinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import com.smashfinance.config.conditions.OnDevProfileCondition;
import com.smashfinance.model.IInitializer;
import com.smashfinance.model.SeedDataInitializer;

@Configuration
public class AppConfiguration {

    @Bean
    @Conditional(OnDevProfileCondition.class)
    public IInitializer devInitializer(AppProperties appProperties) {

        return new SeedDataInitializer(appProperties);
    }

}
