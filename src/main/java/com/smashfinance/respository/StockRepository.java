package com.smashfinance.respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smashfinance.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByCompanyName(String companyName);

    Optional<Stock> findByTickerSymbol(String tickerSymbol);

    void deleteByCompanyName(String companyName);

    void deleteByTickerSymbol(String tickerSymbol);

    void deleteById(Long id);

    Optional<Stock> findById(Long id);

}
