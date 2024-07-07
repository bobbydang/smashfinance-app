export const API_BASE_URL =
  process.env.REACT_APP_API_BASE_URL || "http://localhost:8081/api/v1";

export const STOCK_ENDPOINTS = {
  GET_STOCKS: "/stocks",
  GET_STOCK_DATA: (id: string) => `/stocks/${id}/stockdata`,
};

export function getStockDataApiUrl(stockSymbol: string): string {
  return `${API_BASE_URL}${STOCK_ENDPOINTS.GET_STOCK_DATA(stockSymbol)}`;
}

/* export const POST_ENDPOINTS = {
  GET_POSTS: "/posts",
  ADD_POST: "/posts/add",
  UPDATE_POST: "/posts/update",
  DELETE_POST: "/posts/delete",
  // More post-related endpoints
}; */

// More modules as needed
