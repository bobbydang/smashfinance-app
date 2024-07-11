import axios from "axios";
import { createChart } from "lightweight-charts";
import React, { useEffect, useRef } from "react";
import { getStockDataApiUrl } from "../config/apiConfig";
import StockData from "../interfaces/stockData";
import StockDataChartFormat from "../interfaces/stockDataChartFormat";
import TransformFunction from "../interfaces/transformFunction";

interface OhlcvChartProps {
  symbol: string;
}

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

const OhlcvChart: React.FC<OhlcvChartProps> = ({ symbol }) => {
  const chartContainerRef = useRef(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const endpoint = getStockDataApiUrl(symbol);
        const response = await axios.get(endpoint, {
          timeout: 60000,
        });

        const stockData = await response.data;
        const data = stockDataTransform.transform(stockData);

        if (chartContainerRef.current) {
          const chart = createChart(chartContainerRef.current, {
            width: 1280,
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

  return <div ref={chartContainerRef}></div>;
};

export default OhlcvChart;
