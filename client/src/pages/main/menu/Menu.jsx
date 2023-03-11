import React, {useEffect, useMemo, useState} from 'react';
import classes from './Menu.module.css'

const Menu = ({categories, active, setActive, fetch}) => {

    const [isActive, setIsActive] = useState(true)

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