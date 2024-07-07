package com.smashfinance.services;

import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.smashfinance.dto.StockDTO;
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

    public Stock findByCompanyName(String companyName) {
        return stockRepository.findByCompanyName(companyName);
    }

    public Stock findByTickerSymbol(String tickerSymbol) {
        return stockRepository.findByTickerSymbol(tickerSymbol);
    }

    public Stock save(@NonNull Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteByCompanyName(String companyName) {
        stockRepository.deleteByCompanyName(companyName);
    }

    public void deleteByTickerSymbol(String tickerSymbol) {
        stockRepository.deleteByTickerSymbol(tickerSymbol);
    }

    public List<StockDTO> findAllStockDTO() {
        return stockRepository.findAllStockDTO();
    }
}
