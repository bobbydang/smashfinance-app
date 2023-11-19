package com.smashfinance.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.services.StockDatumService;

@RestController
@RequestMapping("/api/v1/stockdata")
public class StockDataController {

    private final StockDatumService stockDatumService;

    @GetMapping("/ticker/symbol/{symbol}")
    public ResponseEntity<List<StockDatum>> getStockData(
            @PathVariable("symbol") String tickerSymbol) {
        return new ResponseEntity<>(stockDatumService.findByTickerSymbol(tickerSymbol),
                HttpStatus.OK);
    }

    @Autowired
    public StockDataController(StockDatumService stockDatumService) {
        this.stockDatumService = stockDatumService;
    }

}
