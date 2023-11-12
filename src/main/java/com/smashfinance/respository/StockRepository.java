package com.smashfinance.respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.Stock;
import jakarta.transaction.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.companyName = :companyName")
    Optional<Stock> findByCompanyName(String companyName);

    @Query("SELECT s FROM Stock s WHERE s.tickerSymbol = :tickerSymbol")
    Optional<Stock> findByTickerSymbol(String tickerSymbol);

    @Query("DELETE FROM Stock s WHERE s.companyName = :companyName")
    @Modifying
    @Transactional
    void deleteByCompanyName(String companyName);

    @Query("DELETE FROM Stock s WHERE s.tickerSymbol = :tickerSymbol")
    @Modifying
    @Transactional
    void deleteByTickerSymbol(String tickerSymbol);

    void deleteById(Long id);

    Optional<Stock> findById(Long id);

}
