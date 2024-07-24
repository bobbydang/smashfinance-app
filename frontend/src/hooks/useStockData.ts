import axios from "axios";
import {
  buildStockDataDailyEndpoint,
  buildStockDataWeeklyEndpoint,
  buildStockDataMonthlyEndpoint,
  buildStockDataYearlyEndpoint,
} from "../config/apiConfig";
import { useEffect, useState } from "react";

export const useStockData = (symbol: string, initialInterval: string) => {
  const [stockData, setStockData] = useState<any>(null);
  const [currentInterval, setInterval] = useState<string>(initialInterval);

  useEffect(() => {
    const fetchData = async () => {
      let endpoint;
      let timeout = 5000;
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
        console.error(`Error fetching ${currentInterval} data`, error);
      }
    };

    fetchData();
  }, [symbol, currentInterval]);

  return { stockData, currentInterval, setInterval };
};
