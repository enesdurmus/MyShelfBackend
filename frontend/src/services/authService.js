import api from './apiService';
import cookies from 'js-cookie';

const authService = {
    signin: async (email, password) => {
        const response = await api.post('/api/v1/auth/signin', {
            "email": email,
            "password": password
        });
        cookies.set("access_token", response.data.data.token_dto.access_token);
        return response.data.data.user;
    },
    signup: async (email, password) => {
        const response = await api.post(`/api/v1/auth/signup`, {
            "email": email,
            "password": password
        });
        cookies.set("access_token", response.data.data.token_dto.access_token);
        return response.data.data.user;
    },
};

export default authService;