import React, {useState} from 'react';
import classes from './AddressItem.module.css'

const AddressItem = ({title, tel, open, onClick}) => {
    return (
        <div onClick={onClick}>
            <div className={classes.address}>
                <div className={classes.title}>
                    {title}
                </div>
                <div className={classes.wrapper}>
                    <div className={classes.telWrapper}>
                        <div>Телефон:</div>
                        <div>{tel}</div>
                    </div>
                    <div className={classes.timeWrapper}>
                        <div>Часы работы:</div>
                        <div>Открыт до {open}</div>
                    </div>
                </div>
                <div className={classes.icons}>
                    icons
                </div>
            </div>
        </div>
    );
};

export default AddressItem;