package com.smashfinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.StockCompany;
import jakarta.transaction.Transactional;

@Repository
public interface StockCompanyRepository extends JpaRepository<StockCompany, String> {

    @Query("SELECT sc FROM StockCompany sc WHERE sc.name = :companyName")
    @NonNull
    StockCompany findByCompanyName(@NonNull String companyName);

    @Query("SELECT sc FROM StockCompany sc WHERE sc.tickerSymbol = :tickerSymbol")
    @NonNull
    StockCompany findByTickerSymbol(@NonNull String tickerSymbol);

    @Query("DELETE FROM StockCompany sc WHERE sc.name = :companyName")
    @Modifying
    @Transactional
    void deleteByCompanyName(String companyName);

    @Query("DELETE FROM StockCompany sc WHERE sc.tickerSymbol = :tickerSymbol")
    @Modifying
    @Transactional
    void deleteByTickerSymbol(String tickerSymbol);



}
