import React, { Component } from 'react';
import { Route, Routes } from "react-router-dom";
import "./Home.css";
import TopBar from '../../components/topBar/TopBar';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }


    render() {
        return (
            <div className='home'>
                <TopBar>

                </TopBar>
            </div >
        )
    }
}

export default Home;