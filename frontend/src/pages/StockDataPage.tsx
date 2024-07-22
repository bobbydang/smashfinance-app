import React from "react";
import { useParams } from "react-router-dom";
import LineSeriesChart from "../components/LineSeriesChart";
import CandleStickChart from "../components/CandleStickChart";

const StockDataPage: React.FC = () => {
  const { symbol } = useParams<{ symbol: string }>();
  return (
    <div className="main-container">
      <CandleStickChart symbol={symbol} interval="1D"></CandleStickChart>
      <LineSeriesChart symbol={symbol} interval="1W"></LineSeriesChart>
    </div>
  );
};

export default StockDataPage;
