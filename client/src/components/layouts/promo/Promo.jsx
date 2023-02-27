import React, {useState} from 'react';
import s from './Promo.module.css'

const Promo = () => {
    let [copied, setCopied] = useState(false)
    const copy = async (e) => {
        setCopied(true)
        const promo = e.target.value
        console.log(promo);
        await navigator.clipboard.writeText(promo)
    }
    return (
        <div className={s.container}>
            <h2 className={s.title}>Твой промокод</h2>
            <p className={s.text}>Условия использования личного промокода смотрите в разделе «Условия программы лояльности»</p>
            {copied ? <input type="text" className={s.promoCopyActive} value={'Промокод скопирован!'}/> : <input type="text" className={s.promoCopy} value={'CZPR52E3'} onClick={copy}/>}
            <p className={s.text2}>Ты ещё не приглашал друзей!</p>
        </div>
    );
};

export default Promo;