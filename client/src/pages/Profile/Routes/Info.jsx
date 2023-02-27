import React from 'react';
import s from './styles/info.module.css'
const Info = () => {
    return (
        <div className={s.container}>
            <div className={s.profile}></div>
            <div className={s.content}>
                <p className={`${s.name} ${s.contentItem}`}>Валентин</p>
                <p className={`${s.birthday} ${s.contentItem}`}>27/09/2002</p>
                <p className={`${s.number} ${s.contentItem}`}>+79996666666</p>
                <p className={`${s.email} ${s.contentItem}`}>test@mail.ru</p>
                <a href="!#" className={s.editBtn}>Редактировать</a>
            </div>
        </div>
    );
};

export default Info;