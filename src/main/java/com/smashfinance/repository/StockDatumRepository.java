package com.smashfinance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.StockDatum;

@Repository
public interface StockDatumRepository extends JpaRepository<StockDatum, Long> {

        @Query("SELECT sd FROM StockDatum sd WHERE sd.stockCompany.name = :companyName")
        List<StockDatum> findStockDataByCompanyName(String companyName);

        @Query("SELECT sd FROM StockDatum sd JOIN sd.stockCompany sc WHERE sc.tickerSymbol = :tickerSymbol")
        List<StockDatum> findStockDataByTickerSymbol(String tickerSymbol);

        @SuppressWarnings("unchecked")
        @NonNull
        StockDatum save(@NonNull StockDatum stockDatum);

}
