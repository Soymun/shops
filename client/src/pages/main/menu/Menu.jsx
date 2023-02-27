import React, {useEffect, useMemo, useState} from 'react';
import classes from './Menu.module.css'

const Menu = ({categories, active, setActive, fetch}) => {

    const [isActive, setIsActive] = useState(true)
    // const [categories, setCategories] = useState([
    //     {index: 0, value: 'Новинки', categories: [1,2]},
    //     {index: 1, value: 'Кинг комбо', categories: [3]},
    //     {index: 2, value: 'Кинг Сет', categories: [4]},
    //     {index: 3, value: 'Бургеры из говядины'},
    //     {index: 4, value: 'Бургеры из курицы и рыбы'},
    //     {index: 5, value: 'Креветки'},
    //     {index: 6, value: 'Роллы'},
    //     {index: 7, value: 'Соусы'},
    //     {index: 8, value: 'Картошка'},
    //     {index: 9, value: 'Закуски'},
    //     {index: 10, value: 'Холодные напитки'},
    //     {index: 11, value: 'Пиво'},
    //     {index: 12, value: 'Горячие напитки'},
    //     {index: 13, value: 'Молочные коктейли'},
    //     {index: 14, value: 'Десерты'},
    //     {index: 15, value: 'А4 БОКС'},
    //     {index: 16, value: 'Кинг Топ'},
    //     {index: 17, value: 'Дополнительно'},
    //     {index: 18, value: 'Салаты'},
    //     {index: 19, value: 'Купоны'},
    // ])

    const selectCategory = (index) => {
        setActive(index)
    }

    useEffect(() => {
        fetch()
    }, [active])



    return (
        <div className={classes.container}>
            <div className={isActive ? `${classes.menuActive} ${classes.menu}` : classes.menu}>
                <div className={classes.menuContent}>
                    {categories.map(category =>
                        <div onClick={() => selectCategory(category.index)} className={active === category.index ? `${classes.item} ${classes.activeCategory}` : classes.item}>
                            {category.value}
                        </div>
                    )}
                </div>
                <div onClick={() => setIsActive(!isActive)} className={classes.toggle}>
                    {isActive ? 'click!' : 'unclick'}
                </div>
            </div>
        </div>
    );
};

export default Menu;