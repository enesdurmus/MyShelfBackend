import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import "./Users.css";
import cookies from 'js-cookie';
import Navbar from '../../components/navbar/Navbar';

class Users extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    render() {
        return (
            <div className='Users'>
                <Navbar></Navbar>

                <h2>sa</h2>
            </div >
        )
    }
}

export default Users;