package com.smashfinance.entity;

import java.util.List;
import com.smashfinance.repository.StockRepositoryQueryConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
@NamedNativeQueries({
        @NamedNativeQuery(name = "Stock.findWeeklyStockDataByTickerSymbol",
                query = StockRepositoryQueryConstants.FIND_WEEKLY_STOCK_DATA_BY_TICKER_SYMBOL,
                resultClass = StockDatum.class),
        @NamedNativeQuery(name = "Stock.findMonthlyStockDataByTickerSymbol",
                query = StockRepositoryQueryConstants.FIND_MONTHLY_STOCK_DATA_BY_TICKER_SYMBOL,
                resultClass = StockDatum.class),
        @NamedNativeQuery(name = "Stock.findYearlyStockDataByTickerSymbol",
                query = StockRepositoryQueryConstants.FIND_YEARLY_STOCK_DATA_BY_TICKER_SYMBOL,
                resultClass = StockDatum.class)

})
public class Stock {

    @Id
    @Column(name = "symbol", length = 10)
    private String tickerSymbol;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "sector", length = 255)
    private String sector;

    @Column(name = "industry", length = 255)
    private String industry;

    @Column(name = "country", length = 255)
    private String country;

    @OneToMany(mappedBy = "stock")
    private List<StockDatum> stockData;

    public String getName() {
        return name;
    }

    public void setName(String companyName) {
        this.name = companyName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getId() {
        return tickerSymbol;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<StockDatum> getStockData() {
        return stockData;
    }

    public void setStockData(List<StockDatum> stockData) {
        this.stockData = stockData;
    }



}
