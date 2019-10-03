import { FETCH_PEERS_SUCCESS } from "../actionTypes";

const peers = (state = [], action) => {
  switch (action.type) {
    case FETCH_PEERS_SUCCESS:
      return action.payload.data;
    default:
      return state;
  }
};

export default peers;
