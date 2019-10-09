import React from 'react';

class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: ""
        }
    }
    login(username, password) {

    }
    render() {
        return(
            <div class="login-page">
                <div class="form">
                    <form class="login-form">
                        <input type="text" placeholder="Username" onChange={this.setState(state.username, this.value)}/>
                        <input type="password" placeholder="Password"/>
                        <button onClick={this.login(this.state.username, this.state.password)}>Login</button>
                        <p class="message">Not registered? <a href="#">Create an account</a></p>
                    </form>
                </div>
            </div>
        );
    }
}

export default Login;