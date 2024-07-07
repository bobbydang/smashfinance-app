import axios from "axios";
import { useEffect, useState } from "react";

function useFetchData<T, U>(url: string, transform: (data: T) => U) {
  const [data, setData] = useState<U | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    setLoading(true);
    axios
      .get<T>(url)
      .then((response) => {
        const transformedData = transform(response.data);
        setData(transformedData);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, [url, transform]);

  return { data, loading, error };
}

export default useFetchData;
