import React, { useEffect } from 'react';
import { useAuth } from '../../../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import SignupForm from '../../../components/Forms/SignupForm/SignupForm';

const SignUpPage = () => {
    const { user, signup } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            if (user.roles.includes('ADMIN')) {
                navigate('/admin-dashboard');
            } else {
                navigate('/api-portal');
            }
        }
    }, [user, navigate]);

    return (
        <div>
            <SignupForm signup={signup} />
        </div>
    );
};

export default SignUpPage;