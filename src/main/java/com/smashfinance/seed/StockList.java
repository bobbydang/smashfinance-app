package com.smashfinance.seed;

import java.util.ArrayList;
import com.smashfinance.model.Stock;

public class StockList {

    private ArrayList<Stock> stockList = new ArrayList<Stock>();

    public ArrayList<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(ArrayList<Stock> stockList) {
        this.stockList = stockList;
    }

}
