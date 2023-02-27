import React, {useEffect, useState} from 'react';
import classes from './ProductList.module.css'
import ProductCard from "../productCard/ProductCard";
import axios from "axios";

const ProductList = ({cards, setCards, fetchProducts}) => {

    useEffect(() => {
        fetchProducts()
    }, [])

    return (
        <div className={classes.productList}>
            <div className={classes.wrapper}>
                {cards.map(card =>
                    <ProductCard category={card.albumId} img={card.url} price={card.id} title={card.title}/>
                )}
            </div>
        </div>
    );
};

export default ProductList;