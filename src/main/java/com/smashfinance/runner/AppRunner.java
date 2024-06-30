package com.smashfinance.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.smashfinance.entity.IInitializer;

@Component
public class AppRunner implements ApplicationRunner {

    private static Logger logger = LogManager.getLogger(AppRunner.class);
    private final IInitializer initializer;


    public AppRunner(IInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Hello World!");

        initializer.initialize();

    }

}
