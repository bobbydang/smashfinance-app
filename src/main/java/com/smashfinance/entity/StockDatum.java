package com.smashfinance.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "stock_data")
public class StockDatum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "closing_price", precision = 15, scale = 8)
    private BigDecimal closingPrice;

    @Column(name = "adjusted_closing_price", precision = 15, scale = 8)
    private BigDecimal adjustedClosingPrice;

    @Column(name = "opening_price", precision = 15, scale = 8)
    private BigDecimal openingPrice;

    @Column(name = "high_price", precision = 15, scale = 8)
    private BigDecimal highPrice;

    @Column(name = "low_price", precision = 15, scale = 8)
    private BigDecimal lowPrice;

    @Column(name = "volume", precision = 10)
    private BigInteger volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Stock stock;

    @Column(name = "date")
    LocalDate date;

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

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public Stock getStock() {
        return stock;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


}
