import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import Page404 from "../pages/Page404";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import MemberPage from "../pages/MemberPage";
import MemberDetailPage from "../pages/MemberDetailPage";

export const routerConfig = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="*" element={<Page404 />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/member" element={<MemberPage />} />
      <Route path="/member-detail" element={<MemberDetailPage />} />
    </>
  )
);
