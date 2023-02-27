import React from 'react';
import s from './Modal.module.css'
import CloseModal from "../../../UI/CloseModal";

const Modal = (props) => {
    return (
        <div className={s.container}>
            <div className={s.content}>
                <div className={s.titleContainer}><h2 className={s.title}>Личные данные</h2></div>
                <div className={s.body}>
                    <form action="">
                        <input type="text" placeholder={'Имя'} name={'username'}/>
                        <input type="email" placeholder={'Email'} name={'userEmail'}/>
                        <p className={s.description}>Тебе необходимо будет подтвердить e-mail, перейдя по ссылке в
                            письме</p>
                        <input type="text" placeholder={'Дата рождения'} name={'userBirthday'}/>
                        <button className={s.button}>Отправить</button>
                    </form>
                </div>
                <div className={s.close} onClick={props.closeModal}><CloseModal/></div>
            </div>
        </div>
    );
};

export default Modal;