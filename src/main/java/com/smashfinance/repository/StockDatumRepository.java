package com.smashfinance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.StockDatum;

@Repository
public interface StockDatumRepository extends JpaRepository<StockDatum, Long> {

        @Query("SELECT sd FROM StockDatum sd WHERE sd.stock.tickerSymbol = :tickerSymbol")
        List<StockDatum> findDailyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @Query(value = StockDatumRepositoryQueryConstants.FIND_WEEKLY_STOCK_DATA_BY_TICKER_SYMBOL,
                        nativeQuery = true)
        List<StockDatum> findWeeklyStockDataByTickerSymbol(String tickerSymbol);

        @Query(value = StockDatumRepositoryQueryConstants.FIND_MONTHLY_STOCK_DATA_BY_TICKER_SYMBOL,
                        nativeQuery = true)
        List<StockDatum> findMonthlyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @Query(value = StockDatumRepositoryQueryConstants.FIND_YEARLY_STOCK_DATA_BY_TICKER_SYMBOL,
                        nativeQuery = true)
        List<StockDatum> findYearlyStockDataByTickerSymbol(
                        @Param("tickerSymbol") String tickerSymbol);

        @SuppressWarnings("unchecked")
        @NonNull
        StockDatum save(@NonNull StockDatum stockDatum);

}
