import React, {useState} from 'react';
import classes  from './ProductCard.module.css'

const ProductCard = ({img, title, price, category}) => {



    return (
        <div className={classes.productCard}>
            <img className={classes.img} src={img} alt=""/>
            <div className={classes.title}>
                {title}
            </div>
            <div className={classes.price}>
                {price}
            </div>
        </div>
    );
};

export default ProductCard;