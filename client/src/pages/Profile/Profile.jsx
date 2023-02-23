import React from 'react';
import s from './styles/Profile.module.css'
import Info from "./Routes/Info";
import RightMenu from "./RightMenu";

const Profile = () => {
    return (
        <div className={s.container}>
            <div className={s.wrapper}>
                <div className={s.left}>
                    <Info/>
                </div>
                <div className={s.right}>
                    <RightMenu/>
                </div>
            </div>
        </div>
    );
};

export default Profile;