package com.smashfinance.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.repository.StockDatumRepository;

@Service
public class StockDatumService {

    StockDatumRepository stockDatumRepository;

    public StockDatumService(StockDatumRepository stockDatumRepository) {
        this.stockDatumRepository = stockDatumRepository;

    }

    public List<StockDatum> findDailyStockDataByTickerSymbol(String tickerSymbol) {
        return stockDatumRepository.findDailyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findWeeklyStockDataByTickerSymbol(String tickerSymbol) {
        return stockDatumRepository.findWeeklyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findMontlyStockDataByTickerSymbol(String tickerSymbol) {
        return stockDatumRepository.findMonthlyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findYearlyStockDataByTickerSymbol(String tickerSymbol) {
        return stockDatumRepository.findYearlyStockDataByTickerSymbol(tickerSymbol);
    }



}
