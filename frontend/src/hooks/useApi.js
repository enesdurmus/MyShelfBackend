import { useContext } from 'react';
import { ApiContext } from '../contexts/ApiContext';

export const useApi = () => {
    return useContext(ApiContext);
};