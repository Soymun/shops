import './styles/app.css'
import Header from "./components/layouts/header/Header";
import Footer from "./components/layouts/footer/Footer";
import {Route, Routes} from "react-router-dom";
import Main from "./pages/main/Main";
import Profile from "./pages/Profile/Profile";
import Address from "./pages/Profile/Routes/Address";
import Orders from "./pages/Profile/Routes/Orders";

function App() {


    return (
        <div className="App">
            <Header/>
            <Routes>
                <Route path={'/'} element={<Main/>}></Route>
                <Route path={'/profile'} element={<Profile/>}></Route>
                <Route path={'/profile/address'} element={<Address/>}></Route>
                <Route path={'/profile/orders'} element={<Orders/>}></Route>
            </Routes>
            <Footer/>
        </div>
    );
}

export default App;
