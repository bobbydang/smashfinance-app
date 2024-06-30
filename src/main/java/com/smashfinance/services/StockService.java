package com.smashfinance.services;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.smashfinance.entity.Stock;
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

    public Optional<Stock> findById(@NonNull Long id) {
        return stockRepository.findById(id);
    }

    public Optional<Stock> findByCompanyName(String companyName) {
        return stockRepository.findByCompanyName(companyName);
    }

    public Optional<Stock> findByTickerSymbol(String tickerSymbol) {
        return stockRepository.findByTickerSymbol(tickerSymbol);
    }

    public Stock save(@NonNull Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteById(@NonNull Long id) {
        stockRepository.deleteById(id);
    }

    public void deleteByCompanyName(String companyName) {
        stockRepository.deleteByCompanyName(companyName);
    }

    public void deleteByTickerSymbol(String tickerSymbol) {
        stockRepository.deleteByTickerSymbol(tickerSymbol);
    }
}
