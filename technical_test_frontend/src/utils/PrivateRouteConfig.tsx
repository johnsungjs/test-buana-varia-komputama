import { Outlet } from "react-router-dom";

export default function PrivateRoutes() {
  // useEffect(() => {
  //auth method here
  // },[])
  // let auth = {'token': 'true', userRole: 'super'};

  // let auth = JSON.parse(localStorage.getItem("userData") || "[]");

  // const auth = {token: localStorage.getItem("userData")}
  return (
    // auth.token && auth.userRole ? <Outlet /> : <Navigate to="/login" />
    <Outlet />
  );
}
