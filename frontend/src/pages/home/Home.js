import React, { Component } from 'react';
import { Route, Routes } from "react-router-dom";
import "./Home.css";
import Navbar from '../../components/navbar/Navbar';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }


    render() {
        return (
            <div className='Home'>
                <Navbar>

                </Navbar>
            </div >
        )
    }
}

export default Home;