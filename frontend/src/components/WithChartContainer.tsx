// withChartContainer.tsx
import React from "react";
import { IntervalSelector } from "./IntervalSelector";

export const withChartContainer = (
  WrappedComponent: React.ComponentType<any>
) => {
  return (props: any) => (
    <div className="component-container flex-col rounded bg-gray-50">
      <WrappedComponent {...props} />
    </div>
  );
};
