import React, {useState} from 'react';
import classes from "./Header.module.css";
import NavBar from "../navbar/NavBar";

const Header = () => {

    const [active, setActive] = useState(false)

    return (
        <div className={classes.header}>
            <div className={classes.headerMenu}>
                <div className={classes.leftMenu}>
                    <button onClick={() => setActive(!active)} className={classes.navBar}>
                        <NavBar active={active} setActive={setActive}/>
                    </button>
                    <div className={classes.city}>
                        <div className={classes.citySelect}>в ресторане</div>
                        <div className={classes.selectedCity}>Для заказа выберите ресторан</div>
                    </div>
                    <div className={classes.search}>
                        <input className={classes.searchInput} placeholder='Введите название блюда' type="text"/>
                    </div>
                    <div className={classes.logo}>
                        <img
                            src="https://web-static.burgerkingrus.ru/master/25190/_nuxt/85fa0a1c71a8bba230f5de81e911c609.svg"
                            alt=""/>
                    </div>
                </div>
                <div className={classes.rightMenu}>
                    <a href='' className={classes.coupons}>
                        Купоны
                    </a>
                    <a href='' className={classes.club}>
                        KING CLUB
                    </a>
                    <a href="">
                        <div className={classes.profile}></div>
                    </a>
                    <div className={classes.cart}>
                        <button className={classes.cartBtn}></button>
                    </div>
            </div>
        </div>
</div>
)
    ;
};

export default Header;