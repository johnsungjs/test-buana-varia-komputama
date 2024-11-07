import { Navigate, Outlet } from "react-router-dom";

export default function PrivateRoutes() {
  const credential = localStorage.getItem("credential");

  return credential ? <Outlet /> : <Navigate to="/login" />;
}
