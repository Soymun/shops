import React from 'react';
import classes from './Profile.module.css'
const Profile = () => {
    return (
        <div className={classes.container}>
            <div className={classes.wrapper}>
                <div className={classes.left}>
                    <div className={classes.profile}></div>
                    <div className={classes.info}>
                        <h2 className={classes.title}></h2>
                        <p className={classes.infoItem}>27/09/2002</p>
                        <p className={classes.infoItem}>+79012281337</p>
                        <p className={classes.infoItem}>zaycev.vid@yandex.ru</p>
                        <a href="#" className={classes.editBtn}>Редактировать</a>
                    </div>
                </div>
                <div className={classes.right}>
                    <a href="#" className={classes.navBtn}>Информация</a>
                    <a href="#" className={classes.navBtn}>Сохраненные адреса</a>
                    <a href="#" className={classes.navBtn}>История заказов</a>
                </div>
            </div>
        </div>
    );
};

export default Profile;