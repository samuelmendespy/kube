import axios from "axios";

const API_URL = "http://localhost:8080/auth/users/auth.php";

const api = axios.create({
  baseURL: API_URL,
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;