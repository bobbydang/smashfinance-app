import axios from "axios";
import { createChart } from "lightweight-charts";
import React, { useEffect, useRef } from "react";
import { getStockDataApiUrl } from "../config/apiConfig";
import StockData from "../interfaces/stockData";
import StockDataChartFormat from "../interfaces/stockDataChartFormat";
import TransformFunction from "../interfaces/transformFunction";

const stockDataTransform: TransformFunction<
  StockData[],
  StockDataChartFormat[]
> = {
  transform: (data: StockData[]): StockDataChartFormat[] => {
    return data.map((stockData) => {
      const formattedDate = new Date(stockData.date)
        .toISOString()
        .split("T")[0];
      return {
        time: formattedDate,
        open: stockData.openingPrice,
        high: stockData.highPrice,
        low: stockData.lowPrice,
        close: stockData.adjustedClosingPrice,
      };
    });
  },
};

const OhlcvChart: React.FC = () => {
  const chartContainerRef = useRef(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const endpoint = getStockDataApiUrl("AA");
        const response = await axios.get(endpoint, {
          timeout: 60000,
        });

        const stockData = await response.data;
        const data = stockDataTransform.transform(stockData);

        if (chartContainerRef.current) {
          const chart = createChart(chartContainerRef.current, {
            width: 800,
            height: 400,
          });
          const candlestickSeries = chart.addCandlestickSeries();

          candlestickSeries.setData(data);
        }
      } catch (error) {
        console.error("Error rendering chart", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div ref={chartContainerRef}>
      <h1> hello world</h1>
      <h1> again </h1>
    </div>
  );
};

export default OhlcvChart;
