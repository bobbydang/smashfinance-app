package com.smashfinance.repository;

public class StockDatumRepositoryQueryConstants {
    public static final String FIND_WEEKLY_STOCK_DATA_BY_TICKER_SYMBOL = """
            WITH FirstDayOfWeek AS (
                SELECT
                    *,
                    DATE_TRUNC('week', date) AS week_start_date
                FROM
                    stock_data
                WHERE stock_symbol = ?1
            ),
            FirstRecordOfDay AS (
                SELECT
                    *,
                    ROW_NUMBER() OVER (PARTITION BY week_start_date ORDER BY date) AS rn
                FROM
                    FirstDayOfWeek
            )
            SELECT
                *
            FROM
                FirstRecordOfDay
            WHERE
                rn = 1;
            """;

    public static final String FIND_MONTHLY_STOCK_DATA_BY_TICKER_SYMBOL = """
            WITH MonthlyData AS (
                SELECT
                    *,
                    DATE_TRUNC('month', date) AS month_start_date,
                    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('month', date) ORDER BY date) AS rn
                FROM
                    stock_data
                WHERE
                    stock_symbol = ?1
            )
            SELECT
                *
            FROM
                MonthlyData
            WHERE
                rn = 1
            ORDER BY
                date;
            """;


    public static final String FIND_YEARLY_STOCK_DATA_BY_TICKER_SYMBOL = """
            WITH YearlyData AS (
                SELECT
                    *,
                    DATE_TRUNC('year', date) AS year_start_date,
                    ROW_NUMBER() OVER (PARTITION BY DATE_TRUNC('year', date) ORDER BY date) AS rn
                FROM
                    stock_data
                WHERE
                    stock_symbol = ?1
            )
            SELECT
                *
            FROM
                YearlyData
            WHERE
                rn = 1
            ORDER BY
                date;
            """;


}

