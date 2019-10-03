import { SHOW_DIALOG_COMPONENT, HIDE_DIALOG } from "../actionTypes";
import { DIALOG_SHOW_COMPONENT } from "../../dialogs";

const initialState = {
  currentDialog: null
};

const screens = (state = initialState, action) => {
  switch (action.type) {
    case SHOW_DIALOG_COMPONENT: {
      return {
        currentDialog: DIALOG_SHOW_COMPONENT,
        params: action.component
      };
    }
    case HIDE_DIALOG: {
      return {
        currentDialog: null
      };
    }
    default: {
      return state;
    }
  }
};

export default screens;

