import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import * as firebase from 'firebase';

var app = firebase.initializeApp({
    apiKey: "AIzaSyCEoGtHEH2jTPJkmIkoT5bKvICOV8V6Yb4",
    authDomain: "caring-62763.firebaseapp.com",
    databaseURL: "https://caring-62763.firebaseio.com",
    projectId: "caring-62763",
    storageBucket: "",
    messagingSenderId: "653605273054",
    appId: "1:653605273054:web:1df10ccc54af980e19f14f",
    measurementId: "G-VNV158HRJE"
});

ReactDOM.render(<App />, document.getElementById("root"));

serviceWorker.unregister();
