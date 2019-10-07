import React, { Component } from "react";
import { connect } from "react-redux";
import {
  SCREEN_COMPONENTS,
  SCREEN_ANALYSIS,
  SCREEN_MAIN,
  SCREEN_SETTINGS
} from "./screens";

import Toolbar from "./components/Toolbar";
import MainFrame from "./components/MainFrame";
import Footer from "./components/Footer";
import LowerBody from "./components/LowerBody";
// import ComponentView from "./components/ComponentView";
// import AnalysisView from "./components/AnalysisView";
// import MainView from "./components/MainView";
// import ComponentDialog from "./components/ComponentDialog";
// import SettingsView from "./components/SettingsView";

import "./style.css";

class ResourceApp extends Component {
  render() {
    const screen = this.props.screen;
    let screenComponent = null;

    switch (screen) {
      case SCREEN_COMPONENTS: {
        screenComponent = <MainFrame />;
        break;
      }
      case SCREEN_ANALYSIS: {
        screenComponent = <LowerBody />;
        break;
      }
      case SCREEN_MAIN: {
        screenComponent = <MainFrame />
        break;
      }
      case SCREEN_SETTINGS: {
        screenComponent = <MainFrame />
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
