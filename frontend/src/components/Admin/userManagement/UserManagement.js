import React, { useState, useEffect } from 'react';
import './UserManagement.css';
import { useApi } from "../../../hooks/useApi";

const UserManagement = () => {
    const [users, setUsers] = useState([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(10);
    const [pageSize, setPageSize] = useState(10);
    const [visiblePages, setVisiblePages] = useState([]);
    const { fetchApiData } = useApi();

    useEffect(() => {
        fetchUsers(page);
    }, [page]);

    useEffect(() => {
        console.log(pageSize);
    }, [pageSize]);

    useEffect(() => {
        setVisiblePages(calculateVisiblePages());
    }, [page, totalPages]);

    const fetchUsers = async (page) => {
        try {
            const response = await fetchApiData(`/api/v1/users?pageNo=${page - 1}&pageSize=${pageSize}`);
            setUsers(response.data.data);
            setTotalPages(100);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const handleEdit = (userId) => {
        console.log(`Edit user with ID: ${userId}`);
    };

    const handleDelete = (userId) => {
        if (window.confirm('Are you sure you want to delete this user?')) {
            console.log(`Delete user with ID: ${userId}`);
        }
    };

    const handlePageSizeChange = (event) => {
        setPageSize(Number(event.target.value));
        setPage(1);
    };

    const calculateVisiblePages = () => {
        const visiblePagesCount = Math.min(totalPages, 5);
        const startPage = Math.max(1, page - Math.floor(visiblePagesCount / 2));
        const endPage = Math.min(totalPages, startPage + visiblePagesCount - 1);
        return [...Array(endPage - startPage + 1).keys()].map(i => startPage + i);
    };

    return (
        <div>
            <div>
                <h1>User Management</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.display_name}</td>
                                <td>{user.display_name}</td>
                                <td>{user.display_name}</td>
                                <td>
                                    <button onClick={() => handleDelete(user.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className="pagination-controls">
                <div className="pagination">
                    {visiblePages.map((pageNumber) => (
                        <button
                            key={pageNumber}
                            onClick={() => setPage(pageNumber)}
                            className={page === pageNumber ? 'active' : ''}
                        >
                            {pageNumber}
                        </button>
                    ))}
                </div>
                <div className="page-size-selector">
                    <label htmlFor="pageSize">Users per page:</label>
                    <select id="pageSize" value={pageSize} onChange={handlePageSizeChange}>
                        <option value={5}>5</option>
                        <option value={10}>10</option>
                        <option value={25}>25</option>
                        <option value={50}>50</option>
                        <option value={100}>100</option>
                    </select>
                </div>
            </div>
        </div>
    );
};

export default UserManagement;
