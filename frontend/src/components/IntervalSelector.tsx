import React from "react";

interface IntervalSelectorProps {
  currentInterval: string;
  onIntervalChange: (interval: string) => void;
}

export const IntervalSelector: React.FC<IntervalSelectorProps> = ({
  currentInterval,
  onIntervalChange,
}) => (
  <div className="flex mt-4 space-x-4">
    {["1D", "1W", "1M", "1Y"].map((interval) => (
      <button
        key={interval}
        className={`btn-text ${
          currentInterval === interval
            ? "bg-blue-500 text-white"
            : "bg-gray-200 text-black"
        } py-2 px-4 rounded hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-300`}
        onClick={() => onIntervalChange(interval)}
      >
        {interval}
      </button>
    ))}
  </div>
);
