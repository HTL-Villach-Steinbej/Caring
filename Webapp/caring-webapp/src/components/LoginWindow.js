import React from "react";
import * as firebase from 'firebase/app';
import 'firebase/auth';
import 'firebase/database';

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
    firebase.auth().signInWithEmailAndPassword(this.state.username, this.state.password).catch(function(error) {
      // Handle Errors here.
      var errorCode = error.code;
      var errorMessage = error.message;
      // ...
    });
    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
        console.log("user is logged in");
        // User is signed in.
        var displayName = user.displayName;
        var email = user.email;
        var emailVerified = user.emailVerified;
        var photoURL = user.photoURL;
        var isAnonymous = user.isAnonymous;
        var uid = user.uid;
        var providerData = user.providerData;
        // ...
      } else {
        console.log("user is not logged in");
        // User is signed out.
        // ...
      }
    });
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
              placeholder="Email"
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
