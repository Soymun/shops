import React from 'react';
import s from './styles/address.module.css'
import RightMenu from "../RightMenu";

const Address = () => {
    return (
        <div className={s.container}>
            <div className={s.wrapper}>
                <div className={s.left}>
                    <h2 className={s.title}>Сохраненные адреса</h2>
                    <a href="#" className={s.btn}>Добавить новый</a>
                </div>
                <div className={s.right}>
                    <RightMenu/>
                </div>
            </div>
        </div>
    );
};

export default Address;