import React from 'react';
import classes from './Footer.module.css'

const Footer = () => {
    return (
        <div className={classes.footer}>
            <div className={classes.wrapper}>
                <div className={classes.topInfo}>
                    <div className={classes.links}>
                        <a href='' className={classes.linkItem}>Корторативная этика</a>
                        <a href='' className={classes.linkItem}>Информация для гостей</a>
                        <a href='' className={classes.linkItem}>FAQ</a>
                        <a href='' className={classes.linkItem}>Контакты</a>

                    </div>
                    <div className={classes.appLinks}>
                        <a className={classes.appLink} href="">
                            <img
                                src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/2ac2876e9ce7751235fabcfdb4d2dda0.svg"
                                alt=""/>
                        </a>
                        <a className={classes.appLink} href="">
                            <img
                                src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/img/google_play.3e02a41.png"
                                alt=""/>
                        </a>
                        <a className={classes.appLink} href="">
                            <img src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/img/huawei.5e00762.png"
                                 alt=""/>
                        </a>
                        <a className={classes.appLink} href="">
                            <img
                                src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/a577c7b4e7eb42a4b81f3d5a1a3c83f6.svg"
                                alt=""/>
                        </a>
                    </div>
                    <a href='' className={classes.linkSmallItem}>Политика обработки персональных данных</a>
                    <div className={classes.vkWrapper}>

                        <div className={classes.vk}></div>
                    </div>
                </div>
                <div className={classes.rights}>
                    © ООО «Бургер Рус», 2023. Все права защищены. <br/>
                    TM & Copyright 2023 Burger King Corporation. All Rights Reserved.
                </div>
            </div>
        </div>
    );
};

export default Footer;