import React from "react";
import { connect } from "react-redux";
import { logoutUser } from "../redux/actions";

class Header extends React.Component {
 
  handleLogout = () => {
    const { dispatch } = this.props;
    dispatch(logoutUser());
  };

  render = () => {
    const { isLoggingOut, logoutError } = this.props;
    return (
      <div className="Header">
        {this.props.component === "Tracking" ? (
          <a className="track-heading-highlighted" href="/">
            Tracking
          </a>
        ) : (
          <a className="track-heading-highlighted" href="/dashboard">
            Dashboard
          </a>
        )}
        {this.props.component === "Tracking" ? (
          <a className="track-heading" href="/dashboard">
            Dashboard
          </a>
        ) : (
          <a className="track-heading" href="/">
            Tracking
          </a>
        )}
        <button className="button logout-button" onClick={this.handleLogout}>
          Logout
        </button>
        {isLoggingOut && <p className="logoutMessage">Logging Out....</p>}
        {logoutError && <p className="logoutMessage">Error logging out</p>}
      </div>
    );
  };
}
const mapStateToProps = (state) => {
  return {
    isLoggingOut: state.auth.isLoggingOut,
    logoutError: state.auth.logoutError,
  };
};
const mapDispatchToProps = (dispatch) => {
  return {
    dispatch,
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Header);
