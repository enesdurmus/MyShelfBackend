import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import "./Signin.css";
import cookies from 'js-cookie';

class Signin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: 'username',
            password: 'password',
            loading: false,
            error: null,
            success: false
        }
    }

    handleInputChange = (event) => {
        if (event.target.name === "password") {
            document.getElementById(event.target.name).type = "password";
        }
        document.getElementById(event.target.name).style.fontFamily = "Montserrat black";
    }

    handleSignIn = async (event) => {
        const userName = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        const data = {
            "username": userName,
            "password": password,
            "email": userName
        };

        this.setState({ loading: true, error: null });

        try {
            const response = await fetch(`/api/v1/auth/signin`, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })

            const responseData = await response.json();
            cookies.set("access_token", responseData.data.access_token);
            this.setState({ loading: false, success: true });
        } catch (exception) {
            console.error(exception);
            this.setState({ loading: false, error: exception, success: true });
        }
    }

    removeHoverOnFirstClick = (event) => {
        const element = document.getElementById(event.target.name);
        if (element.value === element.defaultValue) {
            element.value = "";
        }
    }

    render() {
        if (this.state.success) {
            return <Redirect to="/home" />;
        }
        return (
            <div className="login">
                <h4>Sign In</h4>
                <form onSubmit={this.handleSubmit}>

                    <div className="text_area">
                        <input
                            type="text"
                            id="username"
                            name="username"
                            defaultValue={this.state.username}
                            onChange={this.handleInputChange}
                            onFocus={this.removeHoverOnFirstClick}
                            className="text_input"
                        />
                    </div>
                    <div className="text_area">
                        <input
                            type="text"
                            id="password"
                            name="password"
                            defaultValue={this.state.password}
                            onChange={this.handleInputChange}
                            onFocus={this.removeHoverOnFirstClick}
                            className="text_input"
                        />
                    </div>
                    <button type="button" onClick={this.handleSignIn}>Sign In</button>

                    {this.state.loading && <img src="loading.gif" alt="Loading..." />}
                    {this.state.error && <div>Error: {this.state.error.message}</div>}
                </form >
            </div >
        )
    }
}

export default Signin;