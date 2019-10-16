import React from "react";
import { connect } from "react-redux";
import { showScreen } from "../redux/actions";

function MainWindow(props) {
  return(
    <div className="mainframe">
        <div className="upper-body">
            <div className="welcome-item">
                <h1 className="welcome-text">Welcome to Caring</h1>
                <h4 className="welcome-subtext">The Website you can hire a car easy and quickly</h4>
            </div>
        </div>
        <div className="heading-tracking">
            <div className="tracking-item">
                <h1 className="tracking-text">Track all out cars </h1>
                <h4 
                  className="teacking-subtext">Click <a 
                                      className="link"
                                      onClick={() => {
                                        props.showScreen('Track');
                                      }}> here</a> to select a car on out map an</h4>
            </div>
      </div>
    </div>
  );
}
const mapStateToProps = state => {
  return { currentScreen: state.screens.currentScreen};
};

export default connect(
  mapStateToProps,
  { showScreen }
)(MainWindow);