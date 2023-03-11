import React, {useMemo} from 'react';
import {Map, Clusterer, Placemark} from "@pbe/react-yandex-maps";
import classes from "./MyMap.module.css";

const MyMap = ({center, zoom, clusterPoints}) => {

    return (
        <div className={classes.myMap}>
            <Map className={classes.map} state={{
                center: center,
                zoom,
                controls: ["zoomControl", "fullscreenControl"],
            }}
                 modules={["control.ZoomControl", "control.FullscreenControl"]}
            >
                <Clusterer
                    options={{
                        preset: 'default#image',
                        hasHint: false,
                        groupByCoordinates: false,
                        clusterIconColor: '#502314',

                    }}
                >
                    {clusterPoints.map((coordinates) => (
                        <Placemark options={{
                            iconLayout: "default#image",
                            iconImageSize: [50, 50],
                            iconImageHref: 'https://web-static.burgerkingrus.ru/master/25190/_nuxt/85fa0a1c71a8bba230f5de81e911c609.svg'
                        }} geometry={coordinates}/>
                    ))}
                </Clusterer>
            </Map>
        </div>
    );
};

export default MyMap;