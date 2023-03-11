import './styles/app.css'
import Header from "./components/layouts/header/Header";
import Footer from "./components/layouts/footer/Footer";
import {Route, Routes, useLocation} from "react-router-dom";
import Main from "./pages/main/Main";
import Profile from "./pages/Profile/Profile";
import Address from "./pages/Profile/Routes/Address";
import Orders from "./pages/Profile/Routes/Orders";
import KingClub from "./pages/KingClub/KingClub";
import {YMaps} from "@pbe/react-yandex-maps";
import MyMap from "./pages/map/mapMenu/myMap/MyMap";
import AddressList from "./pages/map/mapMenu/addressMenu/addressList/AddressList";
import AddressBar from "./pages/map/mapMenu/addressMenu/addressBar/AddressBar";
import MapAddress from "./pages/map/MapAddress";
import {useEffect} from "react";

function App() {
    const routeArr = [
        {path: '/', elem: <Main/>},
        {path: '/profile', elem: <Profile/>},
        {path: '/profile/address', elem: <Address/>},
        {path: '/profile/orders', elem: <Orders/>},
        {path: '/king-club', elem: <KingClub/>},
        {path: '/map', elem: <MapAddress/>},
    ]

    const location = useLocation()

    return (
        <YMaps enterprise query={{
            apikey: 'c2391f37-202f-4f60-828d-6f20fff3f8ea'
        }}>
            <div className="App">
                <Header/>
                <Routes>
                    {routeArr.map(elem =>
                        <Route path={elem.path} element={elem.elem}/>
                    )}
                </Routes>
                {location.pathname === '/map' ? '' : <Footer/>}
            </div>
        </YMaps>
    );
}

export default App;
