import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { ApiProvider } from './contexts/ApiContext';
import './App.css';
import SignInPage from './pages/SignInPage/SigninPage';
import SignUpPage from './pages/SignUpPage/SignupPage';
import NotFoundPage from './pages/NotFoundPage/NotFoundPage';
import AdminPage from './pages/AdminPage/AdminPage';
import ApiPortalPage from './pages/ApiPortalPage/ApiPortalPage';
import UserManagement from './components/Admin/UserManagement/UserManagement';
import AccessTokenManagement from './components/Api/AccessTokenManagement/AccessTokenManagement';

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
            <Route path="//access-token-management" element={<AccessTokenManagement />} />
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