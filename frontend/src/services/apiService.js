import axios from 'axios';
import cookies from 'js-cookie';

const instance = axios.create({
    baseURL: 'http://127.0.0.1:5050',
});

const getAccessTokenFromCookie = () => {
    return cookies.get("access_token");
};

instance.interceptors.request.use(
    config => {
        if (config.authHeader) {
            const accessToken = getAccessTokenFromCookie();
            if (accessToken) {
                config.headers.Authorization = `Bearer ${accessToken}`;
            }
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    response => response,
    error => {
        console.log("saaaaa");
        // return Promise.reject(error);
    }
);

export default instance;