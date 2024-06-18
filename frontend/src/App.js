import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { ApiProvider } from './contexts/ApiContext';
import './App.css';
import SignInPage from './pages/Common/SignInPage/SigninPage';
import SignUpPage from './pages/Common/SignUpPage/SignupPage';
import NotFoundPage from './pages/Common/NotFoundPage/NotFoundPage';
import AdminPage from './pages/Admin/AdminPage/AdminPage';
import ApiPortalPage from './pages/Api/ApiPortalPage/ApiPortalPage';
import UserManagement from './pages/Admin/UserManagement/UserManagement';
import AccessTokenManagement from './pages/Api/AccessTokenManagement/AccessTokenManagement';
import SubscriptionPage from './pages/Api/SubscriptionPage/SubscriptionPage';

const App = () => {
  return (
    <AuthProvider>
      <ApiProvider>
        <Router>
          <Routes>
            <Route path="/" element={<SignInPage />} />
            <Route path="/signin" element={<SignInPage />} />
            <Route path="/signup" element={<SignUpPage />} />
            <Route path="/admin-dashboard" element={<AdminPage />} />
            <Route path="/access-token-management" element={<AccessTokenManagement />} />
            <Route path="/subscriptions" element={<SubscriptionPage />} />
            <Route path="/user-management" element={<UserManagement />} />
            <Route path="/api-portal" element={<ApiPortalPage />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        </Router>
      </ApiProvider>
    </AuthProvider>
  );
};

export default App;