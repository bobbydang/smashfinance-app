package com.smashfinance.respository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.StockDatum;

@Repository
public interface StockDatumRepository extends JpaRepository<StockDatum, Long> {

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName")
        List<StockDatum> findStockDataByCompanyName(String companyName);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName ORDER BY s.date ASC")
        List<StockDatum> findStockDataByCompanyNameOrderByDateAsc(String companyName);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName ORDER BY s.date DESC")
        List<StockDatum> findStockDataByCompanyNameOrderByDateDesc(String companyName);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName AND s.date BETWEEN :startDate AND :endDate")
        List<StockDatum> findStockDataByCompanyNameAndDateBetween(String companyName,
                        LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date ASC")
        List<StockDatum> findStockDataByCompanyNameAndDateBetweenOrderByDateAsc(String companyName,
                        LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.companyName = :companyName AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date DESC")
        List<StockDatum> findStockDataByCompanyNameAndDateBetweenOrderByDateDesc(String companyName,
                        LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol")
        List<StockDatum> findStockDataByTickerSymbol(String tickerSymbol);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol ORDER BY s.date ASC")
        List<StockDatum> findStockDataByTickerSymbolOrderByDateAsc(String tickerSymbol);


        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol ORDER BY s.date DESC")
        List<StockDatum> findStockDataByTickerSymbolOrderByDateDesc(String tickerSymbol);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol AND s.date BETWEEN :startDate AND :endDate")
        List<StockDatum> findStockDataByTickerSymbolAndDateBetween(String tickerSymbol,
                        LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date ASC")
        List<StockDatum> findStockDataByTickerSymbolAndDateBetweenOrderByDateAsc(
                        String tickerSymbol, LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s JOIN s.stock st WHERE st.tickerSymbol = :tickerSymbol AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date DESC")
        List<StockDatum> findStockDataByTickerSymbolAndDateBetweenOrderByDateDesc(
                        String tickerSymbol, LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s WHERE s.date = :date")
        List<StockDatum> findStockDataByDate(String date);

        @Query("SELECT s FROM StockDatum s WHERE s.date BETWEEN :startDate AND :endDate")
        List<StockDatum> findStockDataByDateBetween(LocalDate startDate, LocalDate endDate);

        @Query("SELECT s FROM StockDatum s WHERE s.date BETWEEN :startDate AND :endDate ORDER BY s.date ASC")
        List<StockDatum> findStockDataByDateBetweenOrderByDateAsc(LocalDate startDate,
                        LocalDate endDate);

        @Query("SELECT s FROM StockDatum s WHERE s.date BETWEEN :startDate AND :endDate ORDER BY s.date DESC")
        List<StockDatum> findStockDataByDateBetweenOrderByDateDesc(LocalDate startDate,
                        LocalDate endDate);


}
