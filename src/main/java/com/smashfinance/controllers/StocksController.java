package com.smashfinance.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smashfinance.entity.Stock;
import com.smashfinance.services.StockService;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

    private final StockService stockService;

    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Stock>> getAll() {
        try {
            List<Stock> stocks = stockService.findAll();
            if (stocks == null || stocks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
