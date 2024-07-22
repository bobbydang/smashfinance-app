import axios from "axios";
import { createChart, IChartApi } from "lightweight-charts";
import React, { useEffect, useRef, useState } from "react";
import {
  buildStockDataDailyEndpoint,
  buildStockDataMonthlyEndpoint,
  buildStockDataWeeklyEndpoint,
  buildStockDataYearlyEndpoint,
} from "../config/apiConfig";
import ICandlestickChartFormat from "../interfaces/ICandleStickChartFormat";
import IStockData from "../interfaces/IStockData";
import ITransformFunction from "../interfaces/ITransformFunction";

interface ICandleStickChartProps {
  symbol: string;
  interval: string;
}

const stockDataTransform: ITransformFunction<
  IStockData[],
  ICandlestickChartFormat[]
> = {
  transform: (data: IStockData[]): ICandlestickChartFormat[] => {
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

const CandleStickChart: React.FC<ICandleStickChartProps> = ({
  symbol,
  interval,
}) => {
  const chartContainerRef = useRef<HTMLDivElement>(null);
  const chartRef = useRef<IChartApi | null>(null);
  const timeout = 10000;
  const [stockData, setStockData] = useState<any>(null);
  const [currentInterval, setInterval] = useState<string>(interval);

  useEffect(() => {
    const fetchData = async () => {
      let endpoint;
      switch (currentInterval) {
        case "1D":
          endpoint = buildStockDataDailyEndpoint(symbol);
          break;
        case "1W":
          endpoint = buildStockDataWeeklyEndpoint(symbol);
          break;
        case "1M":
          endpoint = buildStockDataMonthlyEndpoint(symbol);
          break;
        case "1Y":
          endpoint = buildStockDataYearlyEndpoint(symbol);
          break;
        default:
          console.error("Invalid interval");
          return;
      }

      try {
        const response = await axios.get(endpoint, { timeout });
        setStockData(response.data);
      } catch (error) {
        console.error(`Error fetching ${interval} data`, error);
      }
    };

    fetchData();
  }, [currentInterval]);

  useEffect(() => {
    if (stockData && chartContainerRef.current) {
      const data = stockDataTransform.transform(stockData);

      // Clean up the previous chart instance if it exists
      if (chartRef.current) {
        chartRef.current.remove();
      }

      chartRef.current = createChart(chartContainerRef.current, {
        width: chartContainerRef.current.clientWidth,
        height: chartContainerRef.current.clientHeight,
      });

      chartRef.current.timeScale().fitContent();

      const ohlc = chartRef.current.addCandlestickSeries();
      ohlc.setData(data);
    }
  }, [stockData]);

  const handleIntervalChange = (newInterval: string) => {
    setInterval(newInterval);
  };

  useEffect(() => {
    const handleResize = () => {
      if (chartRef.current && chartContainerRef.current) {
        chartRef.current.resize(
          chartContainerRef.current.clientWidth,
          chartContainerRef.current.clientHeight
        );

        chartRef.current.timeScale().fitContent();
      }
    };

    window.addEventListener("resize", handleResize);

    handleResize();

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <div className="component-container flex-col rounded bg-gray-50">
      {/* Chart container */}
      <div
        ref={chartContainerRef}
        className="flex w-full h-full  m-0 p-0 b"
      ></div>

      <div className="w-full flex flex-row justify-end -mt-100">
        <div className="m-0 p-0">
          <div className="flex mt-4 space-x-4">
            <button
              className="btn-text bg-gray-200 text-black py-2 px-4 rounded hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-300"
              onClick={() => handleIntervalChange("1D")}
            >
              1D
            </button>

            <button
              className="btn-text bg-gray-200 text-black py-2 px-4 rounded hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-300"
              onClick={() => handleIntervalChange("1W")}
            >
              1W
            </button>

            <button
              className="btn-text bg-gray-200 text-black py-2 px-4 rounded hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-300"
              onClick={() => handleIntervalChange("1M")}
            >
              1M
            </button>

            <button
              className="btn-text bg-gray-200 text-black py-2 px-4 rounded hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-300"
              onClick={() => handleIntervalChange("1Y")}
            >
              1Y
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CandleStickChart;
