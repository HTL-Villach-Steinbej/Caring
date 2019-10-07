import React from 'react';
import store from "./redux/store";
import { Provider } from "react-redux";
import ResourceApp from './ResourceApp';

function App() {
  return (
    <Provider store={store}>
        <ResourceApp />
    </Provider>
  );
}

export default App;
