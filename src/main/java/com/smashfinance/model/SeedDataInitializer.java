package com.smashfinance.model;

import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeedDataInitializer implements IInitializer {

    @Value("${seed.data.path}")
    private String seedDataPath;

    private final Path path = null;

    private final static Logger logger = LogManager.getLogger(SeedDataInitializer.class);

    public SeedDataInitializer() {
        logger.info("Initializing SeedDataInitializer...");
    }

    @Override
    public void initialize() {
        logger.info("Initializing seed data...");

    }
}
