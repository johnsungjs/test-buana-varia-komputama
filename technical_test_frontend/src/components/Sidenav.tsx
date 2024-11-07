import { ReactNode, useEffect, useState } from "react";
import "../utils/custom-style/SidenavStyle.css";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { ArrowRight, Logout } from "@mui/icons-material";
import { Breadcrumbs, IconButton, Radio } from "@mui/material";

import axios from "axios";
import useGoogleAuth from "../custom-hooks/useGoogleAuth";
import { sideNavRoutes } from "../utils/RouterConfig";

interface SidenavProps {
  children: ReactNode;
}

const Sidenav = ({ children }: SidenavProps) => {
  const navigate = useNavigate();
  const location = useLocation();
  const auth = useGoogleAuth();

  const userData = auth.profile;

  const [showSidebar, setShowSidebar] = useState(true);

  const navbarItems = sideNavRoutes;

  let currentLink = "";
  const crumbs = location.pathname
    .split("/")
    .filter((crumb) => crumb !== "")
    .map((crumb) => {
      currentLink += `/${crumb}`;

      return (
        <Link key={crumb} color="inherit" to={currentLink}>
          {crumb}
        </Link>
      );
    });

  const handleShowChildrenSidebar = (action: any, data: any) => {
    if (data.children.length > 0) {
      if (action.target.nodeName !== "A") {
        action.currentTarget.classList.toggle("showMenu");
      }
    } else {
      navigate(data.path);
    }
  };

  return (
    <>
      <div className={`sidebar ${showSidebar ? "" : "close"}`}>
        <div className="logo-details pt-6">
          <img className="rounded-full" alt="default logo" src={auth.profile?.picture} />
          <span className="logo_name">My App</span>
        </div>
        <ul className="nav-links">
          {navbarItems &&
            navbarItems.map((e: any, index: number) => (
              <li
                className={
                  location.pathname === `/bo/${e.path}`
                    ? "opacity-100 bg-slate-400"
                    : "opacity-70"
                }
                key={index}
                onClick={(action) => handleShowChildrenSidebar(action, e)}
              >
                <div className="icon-link">
                  {e.children.length > 0 ? (
                    <>
                      <span>
                        <img alt="robot" src="/assets/svgs/robot.svg" />
                        <span className="link_name">{e.title}</span>
                      </span>
                    </>
                  ) : (
                    <>
                      <Link to={e.path}>
                        {/* <img
                        alt="robot"
                        src="/assets/svgs/robot.svg"
                        height={50}
                      /> */}
                        <div className="relative min-w-[78px] py-2 text-white text-center">
                          {e.logo}
                        </div>
                        <span className="link_name">{e.title}</span>
                      </Link>
                    </>
                  )}
                  {e.children.length > 0 && (
                    <i className="bx bxs-chevron-down arrow"></i>
                  )}
                </div>
                {e.children.length > 0 ? (
                  <ul className="sub-menu">
                    <li>
                      <Link to={e.path} className="link_name">
                        {e.title}
                      </Link>
                    </li>
                    {e.children.map((f: any, index: number) => (
                      <li key={index}>
                        <Link to={f.path} className="link-name">
                          {f.title}
                        </Link>
                      </li>
                    ))}
                  </ul>
                ) : (
                  <ul className="sub-menu">
                    <li>
                      <Link to={e.path} className="link_name">
                        {e.title}
                      </Link>
                    </li>
                  </ul>
                )}
              </li>
            ))}
          <li>
            {userData && (
              <div className="profile-details">
                <div className="profile-content"></div>
                <div>
                  <div className="profile_name">
                    {userData.name ? userData.name.toUpperCase() : "noname"}
                  </div>
                  <div className="job">app by @johnsungjs</div>
                </div>
                <button
                  onClick={() => {
                    navigate("/login");
                    auth.logout();
                  }}
                >
                  <Logout sx={{ color: "white", width: "78px" }} />
                </button>
              </div>
            )}
          </li>
        </ul>
      </div>
      <section className="home-section flex flex-col h-screen">
        <div className="home-content bg-slate-200">
          <IconButton onClick={() => setShowSidebar(!showSidebar)}>
            <Radio checked={true} />
          </IconButton>
          <span className="text flex items-center w-full h-[58px] overflow-hidden">
            <Breadcrumbs aria-label="breadcrumb" separator={<ArrowRight />}>
              {crumbs}
            </Breadcrumbs>
          </span>
        </div>
        <div className="py-2 bg-transparent h-full overflow-auto">
          {children}
        </div>
      </section>
    </>
  );
};

export default Sidenav;
