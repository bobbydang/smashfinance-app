package com.smashfinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import com.smashfinance.config.conditions.OnDevProfileCondition;
import com.smashfinance.entity.IInitializer;
import com.smashfinance.repository.StockDatumRepository;
import com.smashfinance.repository.StockRepository;
import com.smashfinance.seed.SeedDataInitializer;

@Configuration
public class AppConfiguration {

    @Bean
    @Conditional(OnDevProfileCondition.class)
    public IInitializer devInitializer(AppProperties appProperties, StockRepository stockRepository,
            StockDatumRepository stockDataRepository) {

        return new SeedDataInitializer(appProperties, stockRepository, stockDataRepository);
    }

}
