import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

var firebaseConfig = {
    apiKey: "AIzaSyCEoGtHEH2jTPJkmIkoT5bKvICOV8V6Yb4",
    authDomain: "caring-62763.firebaseapp.com",
    databaseURL: "https://caring-62763.firebaseio.com",
    projectId: "caring-62763",
    storageBucket: "caring-62763.appspot.com",
    messagingSenderId: "653605273054",
    appId: "1:653605273054:web:1df10ccc54af980e19f14f",
    measurementId: "G-VNV158HRJE"
  };

export const myFirebase = firebase.initializeApp(firebaseConfig);
const baseDb = myFirebase.firestore();
export const db = baseDb;