import React from "react";
import { SCREENS } from "../screens";
import { connect } from "react-redux";
import { showScreen } from "../redux/actions";

function Toolbar(props){
    return(
        <div className="toolbar">
            {SCREENS.map(SCREEN => (
                 <div className="toolbar-left-item">
                    <p
                        className="toolbar-item-text"
                        onClick={() => {
                        props.showScreen(SCREEN);
                        }}>
                        {SCREEN}
                    </p>
                </div>
            ))}
            <div className="toolbar-right-item">
                <img
                    height="30px"
                    src="./icons/icons8-account-100.png"
                    id="notification-logo"
                    alt="Notifications Icon"
                />
            </div>
            <div className="toolbar-right-item">
                <img
                    height="30px"
                    src="./icons/icons8-alarm-100.png"
                    id="notification-logo"
                    alt="Notifications Icon"
                />
            </div>
        </div>
    )
}

const mapStateToProps = state => {
    return { currentScreen: state.screens.currentScreen};
  };

export default connect(
    mapStateToProps,
    { showScreen }
)(Toolbar);