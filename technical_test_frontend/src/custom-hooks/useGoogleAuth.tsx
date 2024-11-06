/* eslint-disable @typescript-eslint/no-explicit-any */
import {
  googleLogout,
  TokenResponse,
  useGoogleLogin,
} from "@react-oauth/google";
import axios, { AxiosResponse } from "axios";
import { useEffect, useState } from "react";
import { ProfileResponse } from "../utils/types";

export default function useGoogleAuth() {
  const [user, setUser] = useState<TokenResponse | null>(null);
  const [profile, setProfile] = useState<ProfileResponse | null>(null);

  const credential = localStorage.getItem("credential");
  console.log(credential);

  const login = useGoogleLogin({
    onSuccess: (codeResponse) => {
      console.log("codeResponse", codeResponse);
      localStorage.setItem("credential", codeResponse.access_token);
      setUser(codeResponse);
    },
  });

  const logout = () => {
    googleLogout();
    localStorage.removeItem("credential");
  };

  useEffect(() => {
    if (credential) {
      axios
        .get(
          `https://www.googleapis.com/oauth2/v1/userinfo?access_token=${credential}`
        )
        .then((res: AxiosResponse<ProfileResponse>) => {
          console.log("response get profile ", res);
          setProfile(res.data);
        })
        .catch((err) => console.log("error get profile", err));
    }
  }, [user]);

  return {
    login,
    logout,
    profile,
  };
}
