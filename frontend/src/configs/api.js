import axios from 'axios';
import cookies from 'js-cookie';

const instance = axios.create({
    baseURL: 'http://127.0.0.1:5050',
});

instance.interceptors.request.use(
    config => {
        const accessToken = getAccessTokenFromCookie();
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

const getAccessTokenFromCookie = () => {
    return cookies.get("access_token");
};

export default instance;