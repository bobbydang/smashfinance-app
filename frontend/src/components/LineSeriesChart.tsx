import React, { useEffect } from "react";

import { createChart } from "lightweight-charts";
import IStockData from "../models/interfaces/IStockData";
import ITransformFunction from "../models/interfaces/ITransformFunction";
import { useStockData } from "../hooks/useStockData";
import { useChart } from "../hooks/useChart";
import IChartProps from "../models/interfaces/IChartProps";
import { IntervalSelector } from "./IntervalSelector";

const stockDataTransform: ITransformFunction<IStockData[], ILineChartFormat[]> =
  {
    transform: (data: IStockData[]): ILineChartFormat[] => {
      return data.map((stockData) => ({
        time: new Date(stockData.date).toISOString().split("T")[0],
        value: stockData.adjustedClosingPrice,
      }));
    },
  };

const LineSeriesChart: React.FC<IChartProps> = ({ symbol, interval }) => {
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

      const lineSeries = chartRef.current.addLineSeries();
      lineSeries.setData(data);
    }
  }, [stockData]);

  return (
    <div className="mx-auto w-full h-full">
      <div
        ref={chartContainerRef}
        className="flex w-full h-full m-0 p-0 b"
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

export default LineSeriesChart;
