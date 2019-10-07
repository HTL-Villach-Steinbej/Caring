import { SHOW_SCREEN } from "../actionTypes";
import { SCREEN_MAIN } from "../../screens";

const initialState = {
  currentScreen: SCREEN_MAIN
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