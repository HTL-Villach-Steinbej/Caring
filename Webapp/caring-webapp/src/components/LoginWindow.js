import React, { Component } from "react";
import { connect } from "react-redux";
import { loginUser, showScreen } from "../redux/actions";

class LoginWindow extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: ""
    };
    this.usernameChanged = this.usernameChanged.bind(this);
    this.passwordChanged = this.passwordChanged.bind(this);
  }
  usernameChanged(event) {
    let newUsername = event.target.value;
    this.setState({ username: newUsername });
  }
  passwordChanged(event) {
    let newPassword = event.target.value;
    this.setState({ password: newPassword });
  }
  login = () => {
    const { dispatch } = this.props;
    const { username, password } = this.state;
    dispatch(loginUser(username, password));
  }
  register() {
    this.props.showScreen('Register');
  }
  render() {
    const { loginError, isAuthenticated } = this.props;
    if (isAuthenticated) {
      showScreen('Track')
    } 
    else {
      return (
        <div className="login-page">
          <div className="form">
            <form className="login-form">
              <input
                type="text"
                placeholder="Email"
                value={this.state.username}
                onChange={this.usernameChanged}
              />
              <input
                type="password"
                placeholder="Password"
                value={this.state.password}
                onChange={this.passwordChanged}
              />
              {loginError && (
              <p>
                Incorrect email or password.
              </p>
            )}
              <button onClick={this.login}>Login</button>
              <p className="message">
                Not registered? <a onClick={this.register}>Create an account</a>
              </p>
            </form>
          </div>
        </div>
      );
   }
  }
}

function mapStateToProps(state) {
  return {
    isLoggingIn: state.auth.isLoggingIn,
    loginError: state.auth.loginError,
    isAuthenticated: state.auth.isAuthenticated
  };
}

export default connect(mapStateToProps)(LoginWindow);
