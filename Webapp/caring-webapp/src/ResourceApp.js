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
import RentWindow from "./components/RentWindow";
import LoginWindow from "./components/LoginWindow";
import Footer from "./components/Footer";

import "./style.css";


class ResourceApp extends Component {
  render() {
    const screen = this.props.screen;
    let screenComponent = null;

    switch (screen) {
      case SCREEN_HOME: {
        screenComponent = <MainWindow />
        break;
      }
      case SCREEN_TRACING: {
        screenComponent = <RentWindow />;
        break;
      }
      case SCREEN_REPORT: {
        screenComponent = <RentWindow />;
        break;
      }
      case SCREEN_LOGIN: {
        screenComponent = <LoginWindow />
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
