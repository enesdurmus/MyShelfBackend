import api from './apiService';

const appSettingsService = {
    getAppSettings: async () => {
        const response = await api.get('/api/v1/app_settings', { authHeader: true });
        return response.data;
    },
    getAppSettingById: async (id) => {
        const response = await api.get(`/api/v1/app_settings/${id}`, { authHeader: true });
        return response.data;
    },
    createAppSetting: async (data) => {
        const response = await api.post('/api/v1/app_settings', data, { authHeader: true });
        return response.data;
    },
    updateAppSetting: async (id, data) => {
        const response = await api.put(`/api/v1/app_settings/${id}`, data, { authHeader: true });
        return response.data;
    },
    deleteAppSetting: async (id) => {
        const response = await api.delete(`/api/v1/app_settings/${id}`, { authHeader: true });
        return response.data;
    },
};

export default appSettingsService;