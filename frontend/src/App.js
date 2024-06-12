import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes, Switch } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import SignInPage from './pages/SignInPage/SigninPage';
import SignUpPage from './pages/SignUpPage/SignupPage';
import NotFoundPage from './pages/NotFoundPage/NotFoundPage';
import AdminPage from './pages/AdminPage/AdminPage';
import './App.css';

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<SignInPage />} />
          <Route path="/signin" element={<SignInPage />} />
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/admin-dashboard" element={<AdminPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;