import React, { useEffect } from 'react';
import { useAuth } from '../../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import SignupForm from '../../components/Forms/SignupForm/SignupForm';

const SignUpPage = () => {
    const { user, register } = useAuth();
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
            <h1>Sign Up</h1>
            <SignupForm signup={register} />
        </div>
    );
};

export default SignUpPage;