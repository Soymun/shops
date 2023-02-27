import React, {useEffect, useState} from 'react';
import s from './styles/info.module.css'
import Modal from "../modal/Modal";
import axios from "axios";

const Info = () => {
    const [modal, setModal] = useState(false)
    const changeModal = (e) => {
        e.preventDefault()
        setModal(!modal)
    }
    axios.get('http://localhost:3000/profile').then(res =>{
        console.log(res.data);
    })
    return (
        <div className={s.container}>
            <div className={s.profile}></div>
            <div className={s.content}>
                <p className={`${s.name} ${s.contentItem}`}>Валентин</p>
                <p className={`${s.birthday} ${s.contentItem}`}>27/09/2002</p>
                <p className={`${s.number} ${s.contentItem}`}>+79996666666</p>
                <p className={`${s.email} ${s.contentItem}`}>test@mail.ru</p>
                <button className={s.editBtn} onClick={changeModal}>Редактировать</button>
                {modal && <Modal closeModal={changeModal}/>}
            </div>
        </div>
    );
};

export default Info;