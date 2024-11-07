import { useNavigate } from "react-router-dom";

export default function Page404() {
    const navigate = useNavigate();
    return (
        <div className="w-screen h-screen bg-orange-300 flex items-center justify-center">
            <div className="w-[80%] h-[80%] bg-white rounded-2xl text-center">
                <h1 className="pt-2 text-[8rem] md:text-[12rem] text-orangeMega">404</h1>
                <p className="pb-8 text-2xl md:text-4xl font-bold">OPPS! Page NOT FOUND</p>
                <p className="pb-4 text-xl md:text-3xl">Sorry, the page you're looking for does'nt exist</p>
                <button className="px-6 py-2 rounded-xl bg-orangeMega text-md md:text-2xl text-white hover:opacity-80" onClick={() => navigate(-1)}>RETURN</button>
            </div>
        </div>
    );
}