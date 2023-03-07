import React, {useState} from 'react';
import classes from "./AddressList.module.css";
import address from "../../../../Profile/Routes/Address";
import AddressItem from "../addressItem/AddressItem";

const AddressList = ({setCoords, setZoom}) => {

    const b = (address) => {
        setCoords(address.coords)
        setZoom(17)
    }

    const addresses = [
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.831903, 37.411961]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.763338, 37.565466]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.744522, 37.616378]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.780898, 37.642889]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.793559, 37.435983]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.800584, 37.675638]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.716733, 37.589988]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.775724, 37.560840]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.831903, 37.411961]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.763338, 37.565466]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.744522, 37.616378]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.780898, 37.642889]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.793559, 37.435983]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.800584, 37.675638]},
        {title: '1-й Тушинский проезд, 25', tel: '+8(909)111-22-33доб:4527', open: '23:00', coords: [55.716733, 37.589988]},
        {title: 'Домодедово, просп Академика Туполева 2', tel: '8(495)544-50-00', open: '21:50', coords: [55.775724, 37.560840]},
    ]

    return (
        <>
            {addresses.map(address =>
                <AddressItem onClick={() => b(address)} title={address.title} tel={address.tel} open={address.open} coords={addresses.coords}/>
            )}
        </>
    );
};

export default AddressList;