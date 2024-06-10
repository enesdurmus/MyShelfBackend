import React from 'react';
import { Link } from 'react-router-dom';
import TopBar from '../../components/topBar/TopBar';

const HomePage = () => {
    return (
        <div className="home-page">
            <header>
                <TopBar />
            </header>
            <div className="content">
                <h2>Hoş Geldiniz!</h2>
                <p>Burası ana sayfa içeriği olabilir. İstediğiniz içeriği buraya ekleyebilirsiniz.</p>
            </div>
        </div>
    );
};

export default HomePage;