package com.smashfinance.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smashfinance.services.StockDatumService;

@RestController
@RequestMapping("/api/v1/stockdata")
public class StockDataController {

    private final StockDatumService stockDatumService;

    public StockDataController(StockDatumService stockDatumService) {
        this.stockDatumService = stockDatumService;
    }

}
