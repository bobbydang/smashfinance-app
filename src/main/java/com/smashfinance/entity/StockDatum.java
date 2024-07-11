package com.smashfinance.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;



@Entity
@Table(name = "stock_data")
@NamedNativeQueries({
        @NamedNativeQuery(name = "StockDatum.findWeeklyStockDataByTickerSymbol",
                query = "classpath:sql/findWeeklyStockDataByTickerSymbolQuery.sql",
                resultClass = StockDatum.class),
        @NamedNativeQuery(name = "StockDatum.findMonthlyStockDataByTickerSymbol",
                query = "classpath:sql/findMonthlyStockDataByTickerSymbolQuery.sql",
                resultClass = StockDatum.class),
        @NamedNativeQuery(name = "StockDatum.findYearlyStockDataByTickerSymbol",
                query = "classpath:sql/findYearlyStockDataByTickerSymbolQuery.sql",
                resultClass = StockDatum.class)
// Add more @NamedNativeQuery entries as needed
})

public class StockDatum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "close", precision = 10, scale = 2)
    private BigDecimal closingPrice;

    @Column(name = "adj_close", precision = 10, scale = 2)
    private BigDecimal adjustedClosingPrice;

    @Column(name = "open", precision = 10, scale = 2)
    private BigDecimal openingPrice;

    @Column(name = "high", precision = 10, scale = 2)
    private BigDecimal highPrice;

    @Column(name = "low", precision = 10, scale = 2)
    private BigDecimal lowPrice;

    @Column(name = "volume", precision = 20, scale = 0)
    private BigDecimal volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_symbol", referencedColumnName = "symbol")
    @JsonIgnore
    Stock stock;

    @Column(name = "date")
    LocalDateTime date;

    public BigDecimal getAdjustedClosingPrice() {
        return adjustedClosingPrice;
    }

    public void setAdjustedClosingPrice(BigDecimal adjustedClosingPrice) {
        this.adjustedClosingPrice = adjustedClosingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice.setScale(8);
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice.setScale(8);
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice.setScale(8);
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice.setScale(8);
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Stock getStock() {
        return stock;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


}
