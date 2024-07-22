package com.smashfinance.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smashfinance.dto.StockDTO;
import com.smashfinance.entity.StockDatum;
import com.smashfinance.services.StockService;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

    private final StockService stockService;

    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public ResponseEntity<List<StockDTO>> getAll() {
        try {
            List<StockDTO> stocks = stockService.findAllStockDTO();
            if (stocks == null || stocks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{symbol}/stockdata/daily")
    public ResponseEntity<List<StockDatum>> getStockData(
            @PathVariable("symbol") String tickerSymbol) {

        if (tickerSymbol == null || tickerSymbol.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<StockDatum> stockData = stockService.findDailyStockDataByTickerSymbol(tickerSymbol);

        if (stockData.isEmpty() || stockData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockData, HttpStatus.OK);

    }

    @GetMapping("/{symbol}/stockdata/weekly")
    public ResponseEntity<List<StockDatum>> getWeeklyStockData(
            @PathVariable("symbol") String tickerSymbol) {

        if (tickerSymbol == null || tickerSymbol.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<StockDatum> stockData = stockService.findWeeklyStockDataByTickerSymbol(tickerSymbol);

        if (stockData.isEmpty() || stockData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockData, HttpStatus.OK);

    }

    @GetMapping("/{symbol}/stockdata/monthly")
    public ResponseEntity<List<StockDatum>> getMonthlyStockData(
            @PathVariable("symbol") String tickerSymbol) {

        if (tickerSymbol == null || tickerSymbol.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<StockDatum> stockData = stockService.findMontlyStockDataByTickerSymbol(tickerSymbol);

        if (stockData.isEmpty() || stockData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockData, HttpStatus.OK);

    }

    @GetMapping("/{symbol}/stockdata/yearly")
    public ResponseEntity<List<StockDatum>> getYearlyStockData(
            @PathVariable("symbol") String tickerSymbol) {

        if (tickerSymbol == null || tickerSymbol.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<StockDatum> stockData = stockService.findYearlyStockDataByTickerSymbol(tickerSymbol);

        if (stockData.isEmpty() || stockData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(stockData, HttpStatus.OK);

    }



}
