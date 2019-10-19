import React, { Component } from "react";
import { connect } from "react-redux";
import {
  SCREEN_HOME,
  SCREEN_TRACING,
  SCREEN_REPORT,
  SCREEN_LOGIN
} from "./screens";

import Toolbar from "./components/Toolbar";
import MainWindow from "./components/MainWindow";
import TrackWindow from "./components/TrackWindow";
import LoginWindow from "./components/LoginWindow";

import "./style.css";
import ReportWindow from "./components/ReportWIndow";

class ResourceApp extends Component {
  render() {
    const screen = this.props.screen;
    let screenComponent = null;

    switch (screen) {
      case SCREEN_HOME: {
        screenComponent = <MainWindow />;
        break;
      }
      case SCREEN_TRACING: {
        screenComponent = <TrackWindow />;
        break;
      }
      case SCREEN_REPORT: {
        screenComponent = <ReportWindow />;
        break;
      }
      case SCREEN_LOGIN: {
        screenComponent = <LoginWindow />;
        break;
      }
      default:
        break;
    }
    return (
      <div className="App">
        <div className="header">
          <Toolbar />
        </div>
        <div className="body">{screenComponent}</div>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    screen: state.screens.currentScreen
  };
};

export default connect(
  mapStateToProps,
  {}
)(ResourceApp);
