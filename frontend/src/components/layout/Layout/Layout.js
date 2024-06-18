import React, { useState } from 'react';
import Header from '../Header/Header';
import Sidebar from '../SideBar/Sidebar';

const Layout = ({ children }) => {

    return (
        <div>
            <Header />
            <Sidebar />
            <main>
                {children}
            </main>
        </div>
    );
};

export default Layout;