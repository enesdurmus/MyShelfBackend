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
            cookies.set("access_token", response.data.data.token_dto.access_token);
            return { email };
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