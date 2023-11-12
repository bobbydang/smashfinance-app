package com.smashfinance.seed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.smashfinance.config.AppProperties;
import com.smashfinance.entity.IInitializer;
import com.smashfinance.entity.Stock;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.respository.StockDataRepository;
import com.smashfinance.respository.StockRepository;


@Profile("dev")
public class SeedDataInitializer implements IInitializer {


    private final static Logger logger = LogManager.getLogger(SeedDataInitializer.class);
    private String seedStockPath;
    private String seedStockDataDirectory;


    private StockRepository stockRepository;
    private StockDataRepository stockDataRepository;

    @Autowired
    public SeedDataInitializer(AppProperties appProperties, StockRepository stockRepository,
            StockDataRepository stockDataRepository) {

        seedStockPath = appProperties.getSeedStocksDirectory() + appProperties.getSeedStocksFile();
        seedStockDataDirectory = appProperties.getSeedStockDataDirectory();

        this.stockRepository = stockRepository;
        this.stockDataRepository = stockDataRepository;
    }

    @Override
    public void initialize() {

        logger.info("Initializing SeedDataInitializer...");

        if (!Files.exists(Paths.get(seedStockPath)))
            throw new IllegalStateException("Seed data path does not exist: " + seedStockPath);


        seedStocks();
        seedStockData();

    }

    private void seedStocks() {

        logger.info("Seeding stocks...");

        try (FileInputStream fileInputStream = new FileInputStream(seedStockPath)) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            StockList stockList = mapper.readValue(fileInputStream, StockList.class);

            for (Stock stock : stockList.getStockList()) {
                logger.trace("Saving stock: " + stock.getCompanyName());
                stockRepository.save(stock);
            }
        } catch (Exception e) {
            logger.error("Error seeding stocks: " + e.getMessage());
        }
    }

    private void seedStockData() {

        File directory = new File(seedStockDataDirectory);
        final int dateIdx = 0;
        final int openIdx = 1;
        final int highIdx = 2;
        final int lowIdx = 3;
        final int closeIdx = 4;
        final int adjCloseIdx = 5;
        final int volumeIdx = 6;


        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {

                logger.trace("Seeding stock data: " + file.getName());

                String tickerSymbol = file.getName().substring(0, file.getName().indexOf("."));
                Optional<Stock> stock = stockRepository.findByTickerSymbol(tickerSymbol);

                if (stock.isEmpty())
                    throw new IllegalStateException("Stock not found: " + tickerSymbol);

                try (Reader reader = new FileReader(file)) {

                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

                    for (CSVRecord record : csvParser) {

                        if (record.get(dateIdx).matches("^[a-zA-Z]+$")) {
                            continue;
                        }

                        DateTimeFormatter dateTimeFormatter =
                                DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate localDate =
                                LocalDate.parse(record.get(dateIdx), dateTimeFormatter);

                        BigDecimal openPrice = new BigDecimal(record.get(openIdx));
                        BigDecimal highPrice = new BigDecimal(record.get(highIdx));
                        BigDecimal lowPrice = new BigDecimal(record.get(lowIdx));
                        BigDecimal closePrice = new BigDecimal(record.get(closeIdx));
                        BigDecimal adjClosePrice = new BigDecimal(record.get(adjCloseIdx));
                        BigInteger volume = new BigInteger(record.get(volumeIdx));

                        StockDatum stockDatum = new StockDatum();
                        stockDatum.setDate(localDate);
                        stockDatum.setOpeningPrice(openPrice);
                        stockDatum.setHighPrice(highPrice);
                        stockDatum.setLowPrice(lowPrice);
                        stockDatum.setClosingPrice(closePrice);
                        stockDatum.setAdjustedClosingPrice(adjClosePrice);
                        stockDatum.setVolume(volume);

                        stockDatum.setStock(stock.get());
                        stockDataRepository.save(stockDatum);

                    }


                    csvParser.close();


                } catch (Exception e) {
                    logger.error("Error seeding stock data: " + e.getMessage());
                }
            }
        } else {
            logger.error(
                    "Error seeding stock data: " + seedStockDataDirectory + " is not a directory");
        }

        logger.trace("Finished seeding stock data");

    }
}
