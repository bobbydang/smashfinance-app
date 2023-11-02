package com.smashfinance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {


    private String seedDataPath;

    public String getSeedDataPath() {
        return seedDataPath;
    }

    public void setSeedDataPath(String seedDataPath) {
        this.seedDataPath = seedDataPath;
    }



}
