package com.smashfinance.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.smashfinance.config.AppProperties;

@Component
public class SeedDataInitializer implements IInitializer {


    private final static Logger logger = LogManager.getLogger(SeedDataInitializer.class);

    @Autowired
    public SeedDataInitializer(AppProperties appProperties) {
        logger.info("Initializing SeedDataInitializer...");
        logger.info("Seed data path: {}", appProperties.getSeedDataPath());
    }

    @Override
    public void initialize() {

    }
}
