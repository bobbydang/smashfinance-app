interface TransformFunction<T, U> {
  transform: (data: T) => U;
}

export default TransformFunction;
