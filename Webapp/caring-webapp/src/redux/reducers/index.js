import { combineReducers } from "redux";
import screens from "./screens";
import cars from "./cars";
import auth from "./auth";

export default combineReducers({ screens, cars, auth });
