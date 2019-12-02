import { SHOW_SCREEN } from "../actionTypes";
import { SCREEN_TRACING } from "../../screens";

const initialState = {
  currentScreen: SCREEN_TRACING
};

const screens = (state = initialState, action) => {
  switch (action.type) {
    case SHOW_SCREEN: {
      return {
        currentScreen: action.screen
      };
    }
    default: {
      return state;
    }
  }
};

export default screens;