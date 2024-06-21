import api from './apiService';

const appSettingsService = {
    getUsersByPagination: async (pageNo = 0, pageSize = 10) => {
        const response = await api.get(`/api/v1/users?pageNo=${pageNo}&pageSize=${pageSize}`, { authHeader: true });
        return response.data;
    },
};

export default appSettingsService;