import React from 'react';
import {NavLink} from "react-router-dom";
import s from './styles/orders.module.css'
import RightMenu from "../RightMenu";

const Orders = () => {
    return (
        <div className={s.container}>
            <div className={s.wrapper}>
                <div className={s.left}>
                    <h2 className={s.title}>Мои заказы</h2>
                    <div className={s.content}>
                        <h2 className={s.contentTitle}>Активные заказы</h2>
                        <h2 className={s.contentTitle}>Завершённые заказы</h2>
                    </div>
                </div>
                <div className={s.right}>
                    <RightMenu/>
                </div>
            </div>
        </div>
    );
};

export default Orders;