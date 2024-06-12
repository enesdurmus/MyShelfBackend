import React, { useEffect } from 'react';
import { useAuth } from '../../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import SigninForm from '../../components/Forms/SignInForm/SigninForm';

const SignInPage = () => {
    const { user, login } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            if (user.roles.includes('admin')) {
                navigate('/admin-dashboard');
            } else {
                navigate('/api-portal');
            }
        }
    }, [user, navigate]);

    return (
        <div>
            <h1>Sign In</h1>
            <SigninForm login={login} />
        </div>
    );
};

export default SignInPage;