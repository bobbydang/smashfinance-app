package com.smashfinance.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.respository.StockDatumRepository;

@Service
public class StockDatumService {

    StockDatumRepository stockDatumRepository;

    @Autowired
    public StockDatumService(StockDatumRepository stockDatumRepository) {
        this.stockDatumRepository = stockDatumRepository;

    }

    public List<StockDatum> findByTickerSymbol(String tickerSymbol) {
        return stockDatumRepository.findStockDataByTickerSymbol(tickerSymbol);
    }
}
