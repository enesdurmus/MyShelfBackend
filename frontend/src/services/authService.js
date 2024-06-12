import axios from 'axios';
import cookies from 'js-cookie';

export const authService = {
    signin: async (email, password) => {
        try {
            const response = await axios({
                method: 'post',
                url: 'http://127.0.0.1:5050/api/v1/auth/signin',
                data: {
                    "username": "test",
                    "password": "test"
                }
            });
            response.data.data.user.roles = parseToken(response.data.data.token_dto.access_token).authorities;
            cookies.set("access_token", response.data.data.token_dto.access_token);
            return response.data.data.user;
        } catch (exception) {
            throw new Error(exception.message);
        }
    },
    signup: async (email, password) => {
        return { email };
    },
    logout: () => {
    }
};

const parseToken = (token) => {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};