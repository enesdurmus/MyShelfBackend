import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { ApiProvider } from './contexts/ApiContext';
import './App.css';
import SigninPage from './pages/Signin/SigninPage';
import Dashboard from './pages/Dashboard/DashboardPage';
import SubscriptionPage from './pages/Subscriptions/SubscriptionPage';
import UserManagementPage from './pages/UserManagement/UserManagementPage';
import NotFoundPage from './pages/Common/NotFoundPage/NotFoundPage';
import SignUpPage from './pages/SignupPage/SignupPage';

const App = () => {
  return (
    <AuthProvider>
      <ApiProvider>
        <Router>
          <Routes>
            <Route path="/" element={<SigninPage />} />
            <Route path="/signin" element={<SigninPage />} />
            <Route path="/signup" element={<SignUpPage />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/subscriptions" element={<SubscriptionPage />} />
            <Route path="/user-management" element={<UserManagementPage />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        </Router>
      </ApiProvider>
    </AuthProvider>
  );
};

export default App;