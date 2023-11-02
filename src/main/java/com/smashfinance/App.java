package com.smashfinance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.smashfinance.model.IInitializer;
import com.smashfinance.model.SeedDataInitializer;

@SpringBootApplication
public class App {

    private final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Hello World!");
        IInitializer initializer = new SeedDataInitializer();
        initializer.initialize();
        SpringApplication.run(App.class, args);

    }

}
