import React from "react";
import { connect } from "react-redux";
import { showScreen } from "../redux/actions";

function MainWindow(props) {
  return (
    <div className="main-window">
      <div className="upper-body">
        <div className="welcome-item">
          <h1 className="welcome-text">Welcome to Caring</h1>
          <h4 className="welcome-subtext">
            The Website you can hire a car easy and quickly
          </h4>
        </div>
      </div>
      <div className="container-context">
        <div className="container-tracking">
          <div className="tracking-item">
            <h1 className="tracking-text">Track all out cars </h1>
            <h4 className="tracking-subtext">
              Click{" "}
              <a
                className="link"
                onClick={() => {
                  props.showScreen("Track");
                }}
              >
                {" "}
                here
              </a>{" "}
              to select a car on out map an
            </h4>
          </div>
        </div>
        <div className="container-report">
          <div className="report-item">
            <h1 className="report-text">Report a problem </h1>
            <h4 className="report-subtext">
              Click{" "}
              <a
                className="link-secondary"
                onClick={() => {
                  props.showScreen("Track");
                }}
              >
                {" "}
                here
              </a>{" "}
              to tell us the issue you have
            </h4>
          </div>
        </div>
        <div className="container-about">
          <div className="about-item">
            <h1 className="about-text">About us </h1>
            <h4 className="about-subtext">
              Click{" "}
              <a
                className="link"
                onClick={() => {
                  props.showScreen("Track");
                }}
              >
                {" "}
                here
              </a>{" "}
              to get more information about our Company
            </h4>
          </div>
        </div>
      </div>
    </div>
  );
}
const mapStateToProps = state => {
  return { currentScreen: state.screens.currentScreen };
};

export default connect(
  mapStateToProps,
  { showScreen }
)(MainWindow);
