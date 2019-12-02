import React, { Component } from "react";
import { connect } from "react-redux";
import { Route, Switch } from "react-router-dom";
import ProtectedRoute from "./components/ProtectedRoute";
import TrackWindow from "./components/TrackWindow";
import LoginWindow from "./components/LoginWindow";
import "./style.css";

class ResourceApp extends Component {
  render() {
    const { isAuthenticated, isVerifying } = this.props;
    return (
      <Switch>
        <ProtectedRoute
          exact
          path="/"
          component={TrackWindow}
          isAuthenticated={isAuthenticated}
          isVerifying={isVerifying}
        />
        <Route path="/login" component={LoginWindow} />
      </Switch>
    );
  }
}

const mapStateToProps = state => {
  return {
    screen: state.screens.currentScreen,
    isAuthenticated: state.auth.isAuthenticated,
    isVerifying: state.auth.isVerifying
  };
};

export default connect(
  mapStateToProps,
  {}
)(ResourceApp);
