import React from 'react'
import ReactDOM from 'react-dom'

import AppRoot from './AppRoot.jsx'

import './app.less'

ReactDOM.render(
    <AppRoot who="Marilia"/>, //Componente que será renderizado
    document.getElementById('app') //Onde o componente é renderizado (ou seja, na div do index.html cujo id é "app")
)