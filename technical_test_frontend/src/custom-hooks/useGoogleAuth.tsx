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
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const credential = localStorage.getItem("credential");
  const savedProfile = localStorage.getItem("profile");

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
      setIsLoading(true);
      if (savedProfile !== null) {
        setProfile(JSON.parse(savedProfile));
      } else {
        axios
          .get(
            `https://www.googleapis.com/oauth2/v1/userinfo?access_token=${credential}`
          )
          .then((res: AxiosResponse<ProfileResponse>) => {
            console.log("response get profile ", res);
            setProfile(res.data);
            localStorage.setItem("profile", JSON.stringify(res.data));
          })
          .catch((err) => console.log("error get profile", err))
          .finally(() => setIsLoading(false));
      }
    }
  }, [user, credential, savedProfile]);

  return {
    login,
    logout,
    profile,
    isLoading,
  };
}
