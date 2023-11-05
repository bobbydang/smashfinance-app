package com.smashfinance.seed;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.smashfinance.config.AppProperties;
import com.smashfinance.model.IInitializer;


@Profile("dev")
public class SeedDataInitializer implements IInitializer {


    private final static Logger logger = LogManager.getLogger(SeedDataInitializer.class);
    private final AppProperties appProperties;
    private String seedDataPath;


    public SeedDataInitializer(AppProperties appProperties) {
        System.out.println(".()");

        this.appProperties = appProperties;
        seedDataPath = appProperties.getSeedDataPath();
    }

    @Override
    public void initialize() {

        logger.info("Initializing SeedDataInitializer...");

        if (!Files.exists(Paths.get(appProperties.getSeedDataPath())))
            throw new IllegalStateException(
                    "Seed data path does not exist: " + appProperties.getSeedDataPath());


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        StockList stockList = mapper.readValue(new File(seedDataPath), StockList.class);


        try {


        } catch (Exception e) {
            logger.error("Error initializing seed data: " + e.getMessage());
        }

    }
}
