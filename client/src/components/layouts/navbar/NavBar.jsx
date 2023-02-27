import React from 'react';
import classes from './NavBar.module.css'

const NavBar = ({setActive, active}) => {
    return (
        <div onClick={() => setActive(false)} className={ active ? `${classes.navBar} ${classes.active}` : classes.navBar}>
            <div className={classes.wrapper}>
                <div onClick={e => e.stopPropagation()} className={classes.container}>
                    <div className={classes.titles}>
                        <a href='' className={classes.menuItem}>Меню</a>
                        <a href='' className={classes.menuItem}>Купоны</a>
                        <a href='' className={classes.menuItem}>King Club</a>
                        <a href='' className={classes.menuItem}>Работа</a>
                        <a href='' className={classes.menuItem}>FAQ</a>
                        <a href='' className={classes.menuItem}>Контакты</a>
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