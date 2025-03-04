import axios from "axios";

const CircleOfUniversityAPI = axios.create({
  baseURL: "",
  withCredentials: true,
});

export { CircleOfUniversityAPI };
