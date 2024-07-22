package com.smashfinance.services;

import org.springframework.stereotype.Service;
import com.smashfinance.repository.StockDatumRepository;

@Service
public class StockDatumService {

    StockDatumRepository stockDatumRepository;

    public StockDatumService(StockDatumRepository stockDatumRepository) {
        this.stockDatumRepository = stockDatumRepository;

    }



}
