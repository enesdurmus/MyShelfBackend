import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import "./Navbar.css";
import { Link, NavLink } from "react-router-dom";

class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div className="app">
                <div className="navbar">
                    <nav>
                        <Link to="/home" className="title">
                            Website
                        </Link>
                        <ul>
                            <li>
                                <NavLink to="/users">Users</NavLink>
                            </li>
                            <li>
                                <NavLink to="/app_settings">App Settings</NavLink>
                            </li>
                            <li>
                                <NavLink to="/about">About</NavLink>
                            </li>
                            <li>
                                <NavLink to="/services">Services</NavLink>
                            </li>
                            <li>
                                <NavLink to="/contact">Contact</NavLink>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        );
    }
}

export default Navbar;