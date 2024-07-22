package com.smashfinance.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smashfinance.dto.StockDTO;
import com.smashfinance.entity.Stock;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findByCompanyName(String companyName) {
        return stockRepository.findByCompanyName(companyName);
    }

    public Stock findByTickerSymbol(String tickerSymbol) {
        return stockRepository.findByTickerSymbol(tickerSymbol);
    }

    public List<StockDTO> findAllStockDTO() {
        return stockRepository.findAllStockDTO();
    }

    public List<StockDatum> findDailyStockDataByTickerSymbol(String tickerSymbol) {
        return stockRepository.findDailyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findWeeklyStockDataByTickerSymbol(String tickerSymbol) {
        return stockRepository.findWeeklyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findMontlyStockDataByTickerSymbol(String tickerSymbol) {
        return stockRepository.findMonthlyStockDataByTickerSymbol(tickerSymbol);
    }

    public List<StockDatum> findYearlyStockDataByTickerSymbol(String tickerSymbol) {
        return stockRepository.findYearlyStockDataByTickerSymbol(tickerSymbol);
    }

}
