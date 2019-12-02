import { FETCH_CARS_SUCCESS } from "../actionTypes";

const cars = (state = [], action) => {
  switch (action.type) {
    case FETCH_CARS_SUCCESS:
      return action.payload.data;
    default:
      return state;
  }
};

export default cars;
