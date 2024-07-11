import React from "react";
import OhlcvChart from "../components/OhlcvChart";
import { useParams } from "react-router-dom";

const StockDataPage: React.FC = () => {
  const { symbol } = useParams<{ symbol: string }>();
  return (
    <div>
      <OhlcvChart symbol={symbol}></OhlcvChart>
    </div>
  );
};

export default StockDataPage;
