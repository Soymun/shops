import React from 'react';
import s from './Styles/ClubHeader.module.css'
import KingClubSvg from "../../../UI/KingClubSvg";

const ClubHeader = () => {
    return (
        <div>
            <div className={s.container}>
                <div className={s.wrapper}>
                    <div className={s.left}>
                        <KingClubSvg/>
                        <h2 className={s.title}>Бонусная программа в приложении</h2>
                    </div>
                    <div className={s.right}>
                        <img src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/img/logo.7e01d83.png" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ClubHeader;