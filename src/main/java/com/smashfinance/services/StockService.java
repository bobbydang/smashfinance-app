package com.smashfinance.services;

import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.smashfinance.entity.StockCompany;
import com.smashfinance.repository.StockCompanyRepository;

@Service
public class StockService {

    private final StockCompanyRepository stockRepository;

    public StockService(StockCompanyRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockCompany> findAll() {
        return stockRepository.findAll();
    }

    public StockCompany findByCompanyName(String companyName) {
        return stockRepository.findByCompanyName(companyName);
    }

    public StockCompany findByTickerSymbol(String tickerSymbol) {
        return stockRepository.findByTickerSymbol(tickerSymbol);
    }

    public StockCompany save(@NonNull StockCompany stock) {
        return stockRepository.save(stock);
    }

    public void deleteByCompanyName(String companyName) {
        stockRepository.deleteByCompanyName(companyName);
    }

    public void deleteByTickerSymbol(String tickerSymbol) {
        stockRepository.deleteByTickerSymbol(tickerSymbol);
    }
}
