import React from 'react';
import {Link, NavLink} from "react-router-dom";
import s from './styles/RightMenu.module.css'
const RightMenu = () => {
    let a = {
        color: 'red'
    }
    return (
        <div className={s.container}>
            <Link to={'/profile'} activeClassName={s.active}>Информация</Link>
            <NavLink to={'/profile/address'}>Сохраненные адреса</NavLink>
            <NavLink to={'/profile/orders'}>Заказы</NavLink>
        </div>
    );
};

export default RightMenu;