import { applyMiddleware, createStore } from "redux";
import thunkMiddleware from "redux-thunk";
import { verifyAuth } from "./redux/actions";
import rootReducer from "./redux/reducers";
import createSagaMiddleware from "redux-saga";
import { fetchCarsSaga, fetchRentsSaga } from "./redux/sagas";

const sagaMiddleWare = createSagaMiddleware();

export default function configureStore(persistedState) {
    const store = createStore(
      rootReducer,
      persistedState,
      applyMiddleware(sagaMiddleWare, thunkMiddleware)
    );
    store.dispatch(verifyAuth());
    sagaMiddleWare.run(fetchCarsSaga);
    sagaMiddleWare.run(fetchRentsSaga);
    return store;
}

