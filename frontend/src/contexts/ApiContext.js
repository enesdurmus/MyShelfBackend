import React, { createContext, useContext } from 'react';
import apiService from '../services/apiService';

export const ApiContext = createContext();

export const ApiProvider = ({ children }) => {
    const fetchApiData = async (endpoint) => {
        return await apiService.get(endpoint);
    };

    const postApiData = async (endpoint, data) => {
        return await apiService.post(endpoint, data);
    };

    const value = {
        fetchApiData,
        postApiData,
    };

    return <ApiContext.Provider value={value}>{children}</ApiContext.Provider>;
};