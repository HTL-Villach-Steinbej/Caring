import React from "react";
import store from "./redux/store";
import { Provider } from "react-redux";
import ResourceApp from "./ResourceApp";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <ResourceApp />
      </Router>
    </Provider>
  );
}

export default App;
