import React, {useState} from 'react';
import FooterDesc from "../../components/layouts/footerDesc/FooterDesc";
import Menu from "./menu/Menu";
import ProductList from "./category/productList/ProductList";
import axios from "axios";

const Main = () => {

    const [categories, setCategories] = useState([
        {index: 0, value: 'Новинки', categories: [1, 2]},
        {index: 1, value: 'Кинг комбо', categories: [3]},
        {index: 2, value: 'Кинг Сет', categories: [4]},
        {index: 3, value: 'Бургеры из говядины', categories: [2]},
        {index: 4, value: 'Бургеры из курицы и рыбы', categories: [5]},
        {index: 5, value: 'Креветки', categories: [7]},
        {index: 6, value: 'Роллы', categories: [9]},
        {index: 7, value: 'Соусы', categories: [2]},
        {index: 8, value: 'Картошка', categories: [3]},
        {index: 9, value: 'Закуски', categories: [5]},
        {index: 10, value: 'Холодные напитки', categories: [1,6]},
        {index: 11, value: 'Пиво', categories: [1,7,8]},
        {index: 12, value: 'Горячие напитки', categories: [4,7,8]},
        {index: 13, value: 'Молочные коктейли', categories: [1,2,3]},
        {index: 14, value: 'Десерты', categories: [9,7]},
        {index: 15, value: 'А4 БОКС', categories: [3]},
        {index: 16, value: 'Кинг Топ', categories: [4,5]},
        {index: 17, value: 'Дополнительно', categories: [6,7]},
        {index: 18, value: 'Салаты', categories: [1,2]},
        {index: 19, value: 'Купоны', categories: [3]},


    ])
    const [activeCategory, setActiveCategory] = useState(0)
    const [cards, setCards] = useState([])

    const d = () => {
        let res = 'https://jsonplaceholder.typicode.com/photos?_limit=200';
        for (let j = 0; j < categories[activeCategory].categories.length; j++) {
            res = `${res}&albumId=${+categories[activeCategory].categories[j]}`
        }
        return res
    }

    let res = d()
    async function fetchProducts() {
        const response = await axios.get(`${res}`)
        setCards(response.data)
    }

    return (
        <div>
            <Menu fetch={fetchProducts} active={activeCategory} setActive={setActiveCategory} categories={categories}/>
            <ProductList fetchProducts={fetchProducts} cards={cards} setCards={setCards}/>
            <FooterDesc/>
        </div>
    );
};

export default Main;