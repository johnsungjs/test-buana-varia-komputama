import { RouterProvider } from "react-router-dom"
import { routerConfig } from "./utils/RouterConfig"

function App() {

  return (
    <>
      <RouterProvider router={routerConfig} />
    </>
  )
}

export default App
