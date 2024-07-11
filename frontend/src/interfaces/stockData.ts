interface StockData {
  id: number; // Assuming BigInteger is mapped to number for simplicity
  date: Date;
  openingPrice: number;
  highPrice: number;
  lowPrice: number;
  closingPrice: number;
  adjustedClosingPrice: number;
  volume: number;
}

export default StockData;
