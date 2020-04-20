import { call, put, takeLatest } from "redux-saga/effects";
import {
  FETCH_CARS,
  FETCH_CARS_SUCCESS,
  FETCH_RENTS,
  FETCH_RENTS_SUCCESS
} from "./actions";
import { myFirebase } from "../firebase/firebase";
import { getCars, getRents } from "./mockData";

const HOST_NAME = "http://localhost:8080/Caring_WebService/Caring";
const MOCK_MODE = false;

const get = url =>
  fetch(url)
    .then(response => response.json())
    .catch(e => console.log(e));

export function* fetchCars() {
  try {
    let data = [];
    if (MOCK_MODE) {
      data = getCars();
    } else {
      let cars = yield call(get, HOST_NAME + "/cars");
      for (let i = 0; i < cars.length; i++) {
        data.push(cars[i]);
      }
    }
    yield put({ type: FETCH_CARS_SUCCESS, payload: { data } });
  } catch (ex) {
    console.log(ex);
  }
}

export function* fetchCarsSaga() {
  yield takeLatest(FETCH_CARS, fetchCars);
}

export function* fetchRents() {
  try {
    let data = [];
    if (MOCK_MODE) {
      data = getRents();
    } else {
      let rents = yield call(get, HOST_NAME + "/rents");
      rents = rents.filter((rent) => {
        return (rent.uid === myFirebase.auth().currentUser.uid);
      })
      for (let i = 0; i < rents.length; i++) {
        data.push(rents[i]);
      }
    }
    yield put({ type: FETCH_RENTS_SUCCESS, payload: { data } });
  } catch (ex) {
    console.log(ex);
  }
}

export function* fetchRentsSaga() {
  yield takeLatest(FETCH_RENTS, fetchRents);
}

