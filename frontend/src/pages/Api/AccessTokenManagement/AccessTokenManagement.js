import React, { useState, useEffect } from 'react';
import './AccessTokenManagement.css';
import TopBar from '../../../components/TopBar/TopBar';
import { useApi } from '../../../hooks/useApi';
import { MDBBadge, MDBBtn, MDBTable, MDBTableHead, MDBTableBody } from 'mdb-react-ui-kit';

const AccessTokenManagement = () => {
    const [accessToken, setAccessToken] = useState({});
    const { fetchApiData, postApiData } = useApi();

    useEffect(() => {
        fetchAccessToken();
    }, []);

    const fetchAccessToken = async () => {
        try {
            const results = await fetchApiData('api/v1/access_tokens');
            setAccessToken(results.data.data);
        } catch (exception) {
            console.log(exception);
        }
    }

    const handleCreate = async () => {
        try {
            const results = await postApiData('api/v1/access_tokens');
            setAccessToken(results.data.data);
        } catch (exception) {
            console.log(exception);
        }
    };

    const handleDelete = () => {

    };

    return (
        <div>
            <TopBar></TopBar>
            <MDBTable className="w-100 p-3" align='middle'>
                <MDBTableHead>
                    <tr>
                        <th className='bg-secondary' scope='col'>Token</th>
                        <th className='bg-secondary' scope='col'>Created At</th>
                        <th className='bg-secondary' scope='col'>Updated At</th>
                        <th className='bg-secondary' scope='col'>Actions</th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    <tr>
                        <td>
                            <p className='text-muted mb-0'>{accessToken && accessToken.token}</p>
                        </td>
                        <td>
                            <p className='text-muted mb-0'>{accessToken && accessToken.created_at}</p>
                        </td>
                        <td>
                            <p className='text-muted mb-0'>{accessToken && accessToken.updated_at}</p>
                        </td>
                        <td>
                            <MDBBtn color='link' rounded size='sm' onClick={handleDelete}>
                                Delete
                            </MDBBtn>
                            {!accessToken && (
                                <MDBBtn color='link' rounded size='sm' onClick={handleCreate}>
                                    Create
                                </MDBBtn>
                            )}
                        </td>
                    </tr>
                </MDBTableBody>
            </MDBTable></div>
    );
};

export default AccessTokenManagement;
