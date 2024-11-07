import { Outlet } from "react-router-dom";
import Sidenav from "../components/Sidenav";

export default function AuthLayout() {
  return (
    <Sidenav>
      <Outlet />
    </Sidenav>
  );
}
