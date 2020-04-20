import { combineReducers } from "redux";
import auth from "./auth";
import cars from "./cars";
import rents from "./rents";

export default combineReducers({ auth, cars, rents });