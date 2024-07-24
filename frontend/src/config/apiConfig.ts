export const API_BASE_URL =
  process.env.REACT_APP_API_BASE_URL || "http://localhost:8081/api/v1";

export const STOCK_ENDPOINTS = {
  GET_STOCKS: "/stocks",
  GET_STOCK_DATA_DAILY: (id: string) => `/stocks/${id}/stockdata/daily`,
  GET_STOCK_DATA_WEEKLY: (id: string) => `/stocks/${id}/stockdata/weekly`,
  GET_STOCK_DATA_MONTHLY: (id: string) => `/stocks/${id}/stockdata/monthly`,
  GET_STOCK_DATA_YEARLY: (id: string) => `/stocks/${id}/stockdata/yearly`,
};

export function buildStockDataDailyEndpoint(stockSymbol: string): string {
  return `${API_BASE_URL}${STOCK_ENDPOINTS.GET_STOCK_DATA_DAILY(stockSymbol)}`;
}

export function buildStockDataWeeklyEndpoint(stockSymbol: string): string {
  return `${API_BASE_URL}${STOCK_ENDPOINTS.GET_STOCK_DATA_WEEKLY(stockSymbol)}`;
}

export function buildStockDataMonthlyEndpoint(stockSymbol: string): string {
  return `${API_BASE_URL}${STOCK_ENDPOINTS.GET_STOCK_DATA_MONTHLY(
    stockSymbol
  )}`;
}

export function buildStockDataYearlyEndpoint(stockSymbol: string): string {
  return `${API_BASE_URL}${STOCK_ENDPOINTS.GET_STOCK_DATA_YEARLY(stockSymbol)}`;
}

/* export const POST_ENDPOINTS = {
  GET_POSTS: "/posts",
  ADD_POST: "/posts/add",
  UPDATE_POST: "/posts/update",
  DELETE_POST: "/posts/delete",
  // More post-related endpoints
}; */

// More modules as needed
