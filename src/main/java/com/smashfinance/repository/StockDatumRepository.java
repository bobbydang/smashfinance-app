package com.smashfinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.smashfinance.entity.StockDatum;

@Repository
public interface StockDatumRepository extends JpaRepository<StockDatum, Long> {


        @SuppressWarnings("unchecked")
        @NonNull
        StockDatum save(@NonNull StockDatum stockDatum);

}
