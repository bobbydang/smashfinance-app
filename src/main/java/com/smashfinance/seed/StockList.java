package com.smashfinance.seed;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smashfinance.entity.Stock;

public class StockList {

    @JsonProperty("stocks")
    private ArrayList<Stock> stockList = new ArrayList<Stock>();

    public ArrayList<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(ArrayList<Stock> stockList) {
        this.stockList = stockList;
    }

}
