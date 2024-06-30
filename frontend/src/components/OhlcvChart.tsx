import { createChart } from "lightweight-charts";
import React, { useEffect, useRef } from "react";

const OhlcvChart: React.FC = () => {
  const chartContainerRef = useRef(null);

  useEffect(() => {
    if (chartContainerRef.current) {
      const chart = createChart(chartContainerRef.current, {
        width: 800,
        height: 400,
      });
      const candlestickSeries = chart.addCandlestickSeries();

      const data = [
        {
          time: "2018-12-19",
          open: 141.77,
          high: 170.39,
          low: 120.25,
          close: 145.72,
        },
        {
          time: "2018-12-20",
          open: 145.77,
          high: 178.39,
          low: 121.25,
          close: 143.72,
        },
        // more data points...
      ];

      candlestickSeries.setData(data);
    }
  }, []);

  return (
    <div ref={chartContainerRef}>
      <h1> hello world</h1>
    </div>
  );
};

export default OhlcvChart;
