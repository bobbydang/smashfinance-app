package com.smashfinance.controllers;


import java.util.List;
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

        if (tickerSymbol == null || tickerSymbol.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<StockDatum> stockData = stockDatumService.findByTickerSymbol(tickerSymbol);

        if (stockData.isEmpty() || stockData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockData, HttpStatus.OK);

    }

    public StockDataController(StockDatumService stockDatumService) {
        this.stockDatumService = stockDatumService;
    }

}
