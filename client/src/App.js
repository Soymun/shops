import './styles/app.css'
import Header from "./components/layouts/header/Header";
import Footer from "./components/layouts/footer/Footer";
import {Route, Routes} from "react-router-dom";
import Profile from "./pages/profile/Profile";
import Main from "./pages/main/Main";

function App() {
    return (
        <div className="App">
            <Header/>
            <Routes>
                <Route path={'/'} element={<Main/>}></Route>
                <Route path={'/profile'} element={<Profile/>}></Route>
            </Routes>
            <Footer/>
        </div>
    );
}

export default App;
