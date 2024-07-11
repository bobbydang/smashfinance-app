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
    WHERE
        date::date = week_start_date::date
)
SELECT
    *
FROM
    FirstRecordOfDay
WHERE
    rn = 1;
