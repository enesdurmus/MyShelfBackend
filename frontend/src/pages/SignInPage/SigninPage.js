import React, { useEffect } from 'react';
import { useAuth } from '../../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import SigninForm from '../../components/Forms/SignInForm/SigninForm';

const SignInPage = () => {
    const { user, signin } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            navigate('/admin-dashboard');
            // if (user.roles.includes('admin')) {
            //     navigate('/admin-dashboard');
            // } else {
            //     navigate('/api-portal');
            // }
        }
    }, [user, navigate]);

    return (
        <div>
            <SigninForm signin={signin} />
        </div>
    );
};

export default SignInPage;