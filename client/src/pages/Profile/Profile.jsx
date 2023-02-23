import React from 'react';
import s from './styles/Profile.module.css'
import Info from "./Routes/Info";
import RightMenu from "./RightMenu";
import Promo from "../../components/layouts/promo/Promo";

const Profile = () => {
    return (
        <div className={s.container}>
            <div className={s.wrapper}>
                <div className={s.left}>
                    <Info/>
                    <Promo/>
                </div>
                <div className={s.right}>
                    <RightMenu/>
                </div>
            </div>
        </div>
    );
};

export default Profile;