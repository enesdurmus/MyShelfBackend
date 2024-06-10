import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
    return (
        <div className="home-page">
            <header>
                <NavBar />
            </header>
            <div className="content">
                <h2>Hoş Geldiniz!</h2>
                <p>Burası ana sayfa içeriği olabilir. İstediğiniz içeriği buraya ekleyebilirsiniz.</p>
            </div>
        </div>
    );
};

const NavBar = () => {
    return (
        <nav className="navbar">
            <ul>
                <li><Link to="/">Ana Sayfa</Link></li>
                <li><Link to="/profile">Profil</Link></li>
                <li><Link to="/settings">Ayarlar</Link></li>
                {/* İstediğiniz başka linkleri buraya ekleyebilirsiniz */}
            </ul>
        </nav>
    );
};

export default HomePage;