import React, { Component } from "react";
import { connect } from "react-redux";
import {
  SCREEN_HOME,
  SCREEN_TRACING,
  SCREEN_REPORT,
  SCREEN_LOGIN
} from "./screens";

import Toolbar from "./components/Toolbar";
import MainFrame from "./components/MainFrame";
import Footer from "./components/Footer";
import LowerBody from "./components/LowerBody";

import "./style.css";
import Login from "./components/Login";

class ResourceApp extends Component {
  render() {
    const screen = this.props.screen;
    let screenComponent = null;

    switch (screen) {
      case SCREEN_REPORT: {
        screenComponent = <MainFrame />;
        break;
      }
      case SCREEN_TRACING: {
        screenComponent = <LowerBody />;
        break;
      }
      case SCREEN_HOME: {
        screenComponent = <MainFrame />
        break;
      }
      case SCREEN_LOGIN: {
        screenComponent = <Login />
        break;
      }
      default:
        break;
    }
    return (
    <div className="cmc-app">
        <div className="header">
            <Toolbar />
        </div>
        <div className="body">
          {screenComponent}
        </div>
        <div className="footer">
          <Footer />
        </div>
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
  { }
)(ResourceApp);
