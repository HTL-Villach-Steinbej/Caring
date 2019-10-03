import { combineReducers } from "redux";
import peers from "./peers";
import dialogs from "./dialogs";

export default combineReducers({ peers, dialogs });
