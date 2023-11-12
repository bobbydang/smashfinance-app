package com.smashfinance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {


    private String seedStocksDirectory;
    private String seedStocksFile;
    private String seedStockDataDirectory;

    public String getSeedStocksFile() {
        return seedStocksFile;
    }

    public void setSeedStocksFile(String seedDataFile) {
        this.seedStocksFile = seedDataFile;
    }

    public String getSeedStocksDirectory() {
        return seedStocksDirectory;
    }

    public void setSeedStocksDirectory(String seedStocksDirectory) {
        this.seedStocksDirectory = seedStocksDirectory;
    }

    public String getSeedStockDataDirectory() {
        return seedStockDataDirectory;
    }

    public void setSeedStockDataDirectory(String seedStockDataFile) {
        this.seedStockDataDirectory = seedStockDataFile;
    }



}
