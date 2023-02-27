import React from 'react';
import s from './KingClub.module.css'
import KingClubSvg from "../../UI/KingClubSvg";
import ClubHeader from "./components/ClubHeader";
const KingClub = () => {
    return (
        <div className={s.container}>
            <ClubHeader/>
        </div>
    );
};

export default KingClub;