package com.smashfinance.util;

import java.io.IOException;
import java.util.Properties;

public class QueryLoader {

    private static final Properties queries = new Properties();

    static {
        try {
            queries.load(QueryLoader.class.getClassLoader()
                    .getResourceAsStream("StockDatum.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load queries.properties", e);
        }
    }

    public static String getQuery(String key) {
        return queries.getProperty(key);
    }
}
