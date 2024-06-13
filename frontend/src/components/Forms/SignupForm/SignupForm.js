import React, { useState } from 'react';
import './SignupForm.css';

const SignupForm = ({ signup }) => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(username, email, password);
        signup(username, email, password);
    };

    return (
        <div className="login">
            <h4>Sign Up</h4>
            <form>
                <div className="text_area">
                    <input
                        type="text"
                        id="username"
                        name="username"
                        defaultValue="username"
                        className="text_input"
                        onChange={e => setUsername(e.target.value)}
                    />
                </div>
                <div className="text_area">
                    <input
                        type="text"
                        id="email"
                        name="email"
                        defaultValue="email"
                        className="text_input"
                        onChange={e => setEmail(e.target.value)}
                    />
                </div>
                <div className="text_area">
                    <input
                        type="password"
                        id="password"
                        name="password"
                        defaultValue="password"
                        className="text_input"
                        onChange={e => setPassword(e.target.value)}
                    />
                </div>
                <input
                    type="submit"
                    value="SIGNUP"
                    className="btn"
                    onClick={handleSubmit}
                />
            </form>
        </div>
    );
};

export default SignupForm;