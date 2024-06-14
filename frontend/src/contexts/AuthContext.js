import React, { createContext, useState } from 'react';
import { authService } from '../services/authService';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const signin = async (email, password) => {
        const user = await authService.signin(email, password);
        setUser(user);
        console.log("Signin Success!");
    };

    const signup = async (username, email, password) => {
        const user = await authService.signup(username, email, password);
        setUser(user);
        console.log("Signup Success!");
    };

    const logout = () => {
        authService.logout();
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, signin, signup, logout }}>
            {children}
        </AuthContext.Provider>
    );
};