package com.smashfinance.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    private StockService stockService;

    @GetMapping("/")
    public ResponseEntity<List<Stock>> getAll() {
        return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
    }

    @Autowired
    public StocksController(StockService stockService) {
        this.stockService = stockService;

    }

}
