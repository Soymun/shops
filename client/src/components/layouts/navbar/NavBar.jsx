import React from 'react';
import classes from './NavBar.module.css'
import {NavLink} from "react-router-dom";

const NavBar = ({setActive, active}) => {
    return (
        <div onClick={() => setActive(false)} className={ active ? `${classes.navBar} ${classes.active}` : classes.navBar}>
            <div className={classes.wrapper}>
                <div onClick={e => e.stopPropagation()} className={classes.container}>
                    <div className={classes.titles}>
                        <NavLink to={'/'} className={classes.menuItem}>Меню</NavLink>
                        <NavLink to={'/'} className={classes.menuItem}>Купоны</NavLink>
                        <NavLink to={'/king-club'} className={classes.menuItem}>King Club</NavLink>
                        <NavLink to={'/'} className={classes.menuItem}>Работа</NavLink>
                        <NavLink to={'/'} className={classes.menuItem}>FAQ</NavLink>
                        <NavLink to={'/'} className={classes.menuItem}>Контакты</NavLink>
                    </div>
                    <div className={classes.subtitles}>
                        <a className={classes.menuSmallItem} href=''>Новинки и акции</a>
                        <a className={classes.menuSmallItem} href=''>Развитие</a>
                        <a className={classes.menuSmallItem} href=''>О компании</a>
                        <a className={classes.menuSmallItem} href=''>Тендеры</a>
                        <a className={classes.menuSmallItem} href=''>Рестораны</a>
                        <a className={classes.menuSmallItem} href=''>Для детей</a>
                        <a className={classes.menuSmallItem} href=''>Партнеры</a>
                        <a className={classes.menuSmallItem} href=''>Мобильное приложение</a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NavBar;