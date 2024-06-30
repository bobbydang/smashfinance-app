package com.smashfinance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppProperties {


    private String seedStocksDirectory;
    private String seedStockDataFile;
    private String seedStockDataDirectory;

    public String getSeedStockDataFile() {
        return seedStockDataFile;
    }

    public void setSeedStockDataFile(String seedDataFile) {
        this.seedStockDataFile = seedDataFile;
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
