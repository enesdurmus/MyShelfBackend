import React from 'react';
import { Link } from 'react-router-dom';
import './TopBar.css';
import { useAuth } from '../../../hooks/useAuth';

const TopBar = () => {
    const { user } = useAuth();

    const adminPages = [
        { name: 'Home Page', path: '/home' },
        { name: 'User Management', path: '/user-management' },
        { name: 'App Settings', path: '/app-settings' },
    ];

    const apiPages = [
        { name: 'Home Page', path: '/home' },
        { name: 'Access Token Management', path: '/access-token-management' },
    ];

    return (
        <div className="top-bar">
            <div className="title">
                <Link to="/">
                    <img src="/app-icon.png" alt="App Icon" />
                </Link>
                <span>My Shelf</span>
            </div>
            <div className="page">
                {user.roles.includes("ADMIN") ?
                    adminPages.map((page, index) => (
                        <Link key={index} to={page.path}>
                            {page.name}
                        </Link>
                    )) :
                    apiPages.map((page, index) => (
                        <Link key={index} to={page.path}>
                            {page.name}
                        </Link>
                    ))}
            </div>
        </div>
    );
};

export default TopBar;