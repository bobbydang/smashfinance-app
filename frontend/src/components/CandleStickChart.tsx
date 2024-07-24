import { createChart } from "lightweight-charts";
import React, { useEffect } from "react";
import { useChart } from "../hooks/useChart";
import { useStockData } from "../hooks/useStockData";
import ICandlestickChartFormat from "../models/interfaces/ICandleStickChartFormat";
import IChartProps from "../models/interfaces/IChartProps";
import IStockData from "../models/interfaces/IStockData";
import ITransformFunction from "../models/interfaces/ITransformFunction";
import { IntervalSelector } from "./IntervalSelector";

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

const CandleStickChart: React.FC<IChartProps> = ({ symbol, interval }) => {
  const { stockData, currentInterval, setInterval } = useStockData(
    symbol,
    interval
  );
  const { chartContainerRef, chartRef } = useChart();

  useEffect(() => {
    if (stockData && chartContainerRef.current) {
      const data = stockDataTransform.transform(stockData);

      if (chartRef.current) {
        chartRef.current.remove();
      }

      chartRef.current = createChart(chartContainerRef.current, {
        width: chartContainerRef.current.clientWidth,
        height: chartContainerRef.current.clientHeight,
      });

      chartRef.current.timeScale().fitContent();

      const candleStick = chartRef.current.addCandlestickSeries();
      candleStick.setData(data);
    }
  }, [stockData]);

  return (
    <div className="mx-auto w-full h-full">
      <div
        ref={chartContainerRef}
        className="flex w-full h-full m-0 p-0 "
      ></div>
      <div className="w-full flex flex-row justify-end m-0 p-0">
        <IntervalSelector
          currentInterval={currentInterval}
          onIntervalChange={setInterval}
        />
      </div>
    </div>
  );
};

export default CandleStickChart;
