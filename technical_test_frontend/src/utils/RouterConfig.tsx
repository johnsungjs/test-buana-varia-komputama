import {
  Navigate,
  Route,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import Page404 from "../pages/Page404";
import LoginPage from "../pages/LoginPage";
import MemberPage from "../pages/MemberPage";
import MemberDetailPage from "../pages/MemberDetailPage";
import { CheckBox, Close, People } from "@mui/icons-material";
import HandleRegistration from "../pages/HandleRegistration";
import RejectedRegistration from "../pages/RejectedRegistration";
import PrivateRoutes from "./PrivateRouteConfig";
import AuthLayout from "../layout/AuthLayout";
import AddMemberPage from "../pages/AddMemberPage";

export const sideNavRoutes = [
  {
    title: "Member",
    logo: <People />,
    path: "member",
    element: <MemberPage />,
    children: [],
  },
  {
    title: "Manage Registration",
    logo: <CheckBox />,
    path: "manage-registration",
    element: <HandleRegistration />,
    children: [],
  },
  {
    title: "Rejected Members",
    logo: <Close />,
    path: "rejected-registration",
    element: <RejectedRegistration />,
    children: [],
  },
];

export const routerConfig = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="*" element={<Page404 />} />
      <Route path="/" element={<Navigate to={"/login"} />} />
      <Route path="/login" element={<LoginPage />} />

      <Route element={<PrivateRoutes />}>
        <Route element={<AuthLayout />}>
          <Route path="/member" element={<MemberPage />} />
          <Route path="/member/:credential" element={<MemberDetailPage />} />
          <Route path="/manage-registration" element={<HandleRegistration />} />
          <Route path="/member/add" element={<AddMemberPage />} />
          <Route
            path="/rejected-registration"
            element={<RejectedRegistration />}
          />
        </Route>
      </Route>
    </>
  )
);
