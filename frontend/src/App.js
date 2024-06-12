import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import './App.css';
import SignInPage from './pages/SignInPage/SigninPage';
import SignUpPage from './pages/SignUpPage/SignupPage';
import NotFoundPage from './pages/NotFoundPage/NotFoundPage';
import AdminPage from './pages/AdminPage/AdminPage';
import ApiPortalPage from './pages/ApiPortalPage/ApiPortalPage';

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<SignInPage />} />
          <Route path="/signin" element={<SignInPage />} />
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/admin-dashboard" element={<AdminPage />} />
          <Route path="/api-portal" element={<ApiPortalPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;