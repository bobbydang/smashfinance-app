package com.smashfinance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.smashfinance.dto.StockDTO;
import com.smashfinance.entity.Stock;
import com.smashfinance.entity.StockDatum;
import jakarta.transaction.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

        @Query("SELECT s FROM Stock s WHERE s.name = :companyName")
        @NonNull
        Stock findByCompanyName(@NonNull String companyName);

        @Query("SELECT s FROM Stock s WHERE s.tickerSymbol = :tickerSymbol")
        @NonNull
        Stock findByTickerSymbol(@NonNull String tickerSymbol);

        @Query("DELETE FROM Stock s WHERE s.name = :companyName")
        @Modifying
        @Transactional
        void deleteByCompanyName(@NonNull String companyName);

        @Query("DELETE FROM Stock s WHERE s.tickerSymbol = :tickerSymbol")
        @Modifying
        @Transactional
        void deleteByTickerSymbol(String tickerSymbol);

        @Query("SELECT sd FROM Stock s JOIN s.stockData sd WHERE s.tickerSymbol = :tickerSymbol")
        List<StockDatum> findDailyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @Query(name = "Stock.findWeeklyStockDataByTickerSymbol", nativeQuery = true)
        List<StockDatum> findWeeklyStockDataByTickerSymbol(String tickerSymbol);

        @Query(name = "Stock.findMonthlyStockDataByTickerSymbol", nativeQuery = true)
        List<StockDatum> findMonthlyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @Query(name = "Stock.findYearlyStockDataByTickerSymbol", nativeQuery = true)
        List<StockDatum> findYearlyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @Query("SELECT new com.smashfinance.dto.StockDTO(s.name, s.tickerSymbol) FROM Stock s")
        List<StockDTO> findAllStockDTO();


}


