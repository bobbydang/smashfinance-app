interface ITransformFunction<T, U> {
  transform: (data: T) => U;
}

export default ITransformFunction;
