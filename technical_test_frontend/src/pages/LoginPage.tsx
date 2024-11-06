import { GoogleLogin } from "@react-oauth/google";
import useGoogleAuth from "../custom-hooks/useGoogleAuth";

export default function LoginPage() {
  const auth = useGoogleAuth();

  const handleLogin = () => {
    auth.login();
  };

  return (
    <>
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <div className=" relative flex flex-col m-6 space-y-8 bg-white shadow-2xl rounded-2xl md:flex-row md:space-y-0">
          {/* left side */}
          <div className="flex flex-col justify-center p-8 md:p-14">
            <span className="mb-3 text-4xl text-orangeMega font-bold">
              Welcome to Our App
            </span>
            {!auth.profile && (
              <span className="font-light text-gray-400 mb-8">
                Please login
              </span>
            )}
            {auth.profile ? (
              <>
                <div>Name: </div>
                <div>{auth.profile.name}</div>
                <div>email</div>
                <div>{auth.profile.email}</div>
              </>
            ) : (
              <>
                <button
                  onClick={handleLogin}
                  className="w-full flex border-2 items-center border-slate-300 rounded-md py-2 px-4"
                >
                  <img
                    src="/assets/images/google.png"
                    className="aspect-square h-8 w-8"
                  />
                  <div className="pl-4 w-full text-left">
                    Sign In With Google
                  </div>
                </button>
              </>
            )}
            {/* <div className="w-full">
              <button
                className="w-full border-2 border-red-500"
                onClick={() => auth.logout()}
              >
                Logout
              </button>
            </div> */}
          </div>
          {/* left side ends here*/}

          {/* right side */}
          <div className="relative">
            <img
              src="/assets/images/login-image.jpg"
              alt="img"
              className="w-[400px] h-full hidden rounded-r-2xl object-cover md:block"
            />
          </div>
          {/* left side ends here*/}
        </div>
      </div>
    </>
  );
}
