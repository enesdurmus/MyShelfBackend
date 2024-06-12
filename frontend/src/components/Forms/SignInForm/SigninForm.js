import React, { useState } from 'react';
import './SigninForm.css';

const SigninForm = ({ signin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        signin(email, password);
    };

    return (
        <div className="login">
            <h4>Signin</h4>
            <form>
                <div className="text_area">
                    <input
                        type="text"
                        id="username"
                        name="username"
                        defaultValue="username"
                        className="text_input"
                    />
                </div>
                <div className="text_area">
                    <input
                        type="password"
                        id="password"
                        name="password"
                        defaultValue="password"
                        className="text_input"
                    />
                </div>
                <input
                    type="submit"
                    value="SIGNIN"
                    className="btn"
                    onClick={handleSubmit}
                />
            </form>
            <a className="link" href="/signup">Sign Up</a>
        </div>
    );
};

export default SigninForm;