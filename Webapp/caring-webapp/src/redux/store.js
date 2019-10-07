import { createStore, applyMiddleware, compose } from "redux";
import createSagaMiddleware from "redux-saga";

import index from "./reducers";
import {
  fetchPeersSaga
} from "./sagas";

const sagaMiddleWare = createSagaMiddleware();

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export default createStore(
  index,
  composeEnhancers(applyMiddleware(sagaMiddleWare))
);

sagaMiddleWare.run(fetchPeersSaga);
