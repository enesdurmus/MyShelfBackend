import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './TopBar.css';
import { useAuth } from '../../hooks/useAuth';
import {
    MDBContainer,
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBNavbarLink
} from 'mdb-react-ui-kit';

const TopBar = () => {
    const { user } = useAuth();
    const [activePage, setactivePage] = useState('home');

    const adminPages = [
        { name: 'Home Page', path: '/home' },
        { name: 'User Management', path: '/user-management' },
        { name: 'App Settings', path: '/app-settings' },
    ];

    const apiPages = [
        { name: 'Home Page', path: '/home' },
        { name: 'Access Token Management', path: '/access-token-management' },
        { name: 'Subscriptions', path: '/subscriptions' },
    ];

    return (
        <MDBNavbar expand='lg' className='p-3 mb-2 bg-secondary bg-gradient'>
            <MDBContainer fluid>
                <MDBNavbarBrand className='text-white' href='#'>
                    <img
                        src='https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.webp'
                        height='30'
                        alt=''
                        loading='lazy'
                    />
                    My Shelf Api
                </MDBNavbarBrand>
                <MDBNavbarNav>
                    {/* <MDBNavbarItem>
                        <MDBNavbarLink active aria-current='page' href='#'>
                            Home
                        </MDBNavbarLink>
                    </MDBNavbarItem> */}
                    {user && user.roles.includes("ADMIN") ?
                        adminPages.map((page, index) => (
                            <MDBNavbarItem key={index}>
                                <MDBNavbarLink className='text-white' href={page.path}>{page.name} bgColor='light'</MDBNavbarLink>
                            </MDBNavbarItem>
                        )) :
                        apiPages.map((page, index) => (
                            <MDBNavbarItem key={index}>
                                <MDBNavbarLink className='text-white' href={page.path}>{page.name}</MDBNavbarLink>
                            </MDBNavbarItem>
                        ))
                    }
                </MDBNavbarNav>
            </MDBContainer>
        </MDBNavbar>
    );
};

export default TopBar;