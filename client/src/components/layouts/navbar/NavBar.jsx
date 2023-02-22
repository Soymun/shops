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
                        <a href=''>Новинки и акции</a>
                        <a href=''>Развитие</a>
                        <a href=''>О компании</a>
                        <a href=''>Тендеры</a>
                        <a href=''>Рестораны</a>
                        <a href=''>Для детей</a>
                        <a href=''>Партнеры</a>
                        <a href=''>Мобильное приложение</a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NavBar;