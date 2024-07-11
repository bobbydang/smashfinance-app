package com.smashfinance.dto;

public class StockDTO {
    private String companyName;
    private String tickerSymbol;

    public StockDTO(String companyName, String tickerSymbol) {
        this.companyName = companyName;
        this.tickerSymbol = tickerSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }


}
