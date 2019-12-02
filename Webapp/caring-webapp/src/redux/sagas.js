import { call, put, takeLatest } from "redux-saga/effects";
import {
  FETCH_CARS,
  FETCH_CARS_SUCCESS
} from "./actionTypes";
import { getCars } from "./mockData";

const HOST_NAME = "http://192.168.211.175:8080/";
const MOCK_MODE = true;

const get = url =>
  fetch(url)
    .then(response => response.json())
    .catch(e => console.log(e));

export function* fetchCars(action) {
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

export function* fetchCarsSaga(params) {
  yield takeLatest(FETCH_CARS, fetchCars);
}
