import React from "react";
import { useParams } from "react-router-dom";
import LineSeriesChart from "../components/LineSeriesChart";
import CandleStickChart from "../components/CandleStickChart";
import { withChartContainer } from "../components/WithChartContainer";

const LineSeriesChartWithContainer = withChartContainer(LineSeriesChart);
const CandleStickChartWithContainer = withChartContainer(CandleStickChart);

const StockDataPage: React.FC = () => {
  const { symbol } = useParams<{ symbol: string }>();
  return (
    <div className="main-container">
      <CandleStickChartWithContainer symbol={symbol} interval="1D" />
      <LineSeriesChartWithContainer symbol={symbol} interval="1W" />
    </div>
  );
};

export default StockDataPage;
