import { FETCH_RENTS_SUCCESS } from "../actions";

const rents = (state = [], action) => {
  switch (action.type) {
    case FETCH_RENTS_SUCCESS:
      return action.payload.data;
    default:
      return state;
  }
};

export default rents;

