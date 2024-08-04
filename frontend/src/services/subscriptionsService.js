import api from './apiService';

const subscriptionsService = {
    getSubscriptions: async () => {
        const response = await api.get('/api/v1/subscriptions', { authHeader: true });
        return response.data;
    },
    getSubscriptionById: async (id) => {
        const response = await api.get(`/api/v1/subscriptions/${id}`, { authHeader: true });
        return response.data;
    },
    createSubscription: async (data) => {
        const response = await api.post('/api/v1/subscriptions', data, { authHeader: true });
        return response.data;
    },
    updateSubscription: async (id, data) => {
        const response = await api.put(`/api/v1/subscriptions/${id}`, data, { authHeader: true });
        return response.data;
    },
    deleteSubscription: async (id) => {
        const response = await api.delete(`/api/v1/subscriptions/${id}`, { authHeader: true });
        return response.data;
    },
};

export default subscriptionsService;