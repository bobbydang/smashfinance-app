package com.smashfinance.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.smashfinance.model.IInitializer;
import com.smashfinance.model.SeedDataInitializer;

public class AppRunner implements ApplicationRunner {

    private static Logger logger = LogManager.getLogger(AppRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Hello World!");
        IInitializer initializer = new SeedDataInitializer();
        initializer.initialize();

    }

}
