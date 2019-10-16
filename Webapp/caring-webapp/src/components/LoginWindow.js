import React from "react";

class LoginWindow extends React.Component {
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
  login() {
    console.log(
      "You clicked login with username: " +
        this.state.username +
        " and password: " +
        this.state.password
    );
  }
  register() {
    console.log("Now you can register");
  }
  render() {
    return (
      <div className="login-page">
        <div className="form">
          <form className="login-form">
            <input
              type="text"
              placeholder="Username"
              value={this.state.componentName}
              onChange={this.usernameChanged}
            />
            <input
              type="password"
              placeholder="Password"
              value={this.state.componentName}
              onChange={this.passwordChanged}
            />
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

export default LoginWindow;
