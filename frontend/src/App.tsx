import React, { Component } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import StockPage from "./pages/StockPage";

import "./App.css";
import MainMenuNavBar from "./components/MainMenuNavBar";
import StockDataPage from "./pages/StockDataPage";

class App extends Component<{}, {}> {
  render() {
    return (
      // add tailwindcss button
      <Router>
        <div className="App responsive">
          <MainMenuNavBar />
          <Routes>
            <Route path="/stocks" element={<StockPage />} />
            <Route
              path="/stocks/:symbol/stockdata"
              element={<StockDataPage />}
            />
          </Routes>
        </div>
      </Router>
    );
  }
}

export default App;
