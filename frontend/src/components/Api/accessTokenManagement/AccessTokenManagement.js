import React, { useState, useEffect } from 'react';
import api from '../../../services/api';
import './AccessTokenManagement.css';
import TopBar from '../../Common/TopBar/TopBar';

const AccessTokenManagement = () => {
    const [users, setUsers] = useState([]);

    const handleEdit = (userId) => {
        console.log(`Edit user with ID: ${userId}`);
    };

    const handleDelete = (userId) => {
        if (window.confirm('Are you sure you want to delete this user?')) {
            console.log(`Delete user with ID: ${userId}`);
        }
    };

    return (
        <div>
            <TopBar></TopBar>
            <div>
                <h1>Access Token Management</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Token</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <td>aa</td>
                        <td>aa</td>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AccessTokenManagement;
