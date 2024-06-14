import React, { useState } from 'react';
import './SigninForm.css';
import {
    MDBBtn,
    MDBContainer,
    MDBCard,
    MDBCardBody,
    MDBRow,
    MDBCol,
    MDBInput,
    MDBCheckbox,
    MDBIcon
} from 'mdb-react-ui-kit';

const SigninForm = ({ signin }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        signin(email, password);
    };

    return (
        <div className='container'>
            <MDBContainer className='my-5'>
                <MDBCard>

                    <MDBRow className='g-0 d-flex align-items-center'>

                        <MDBCardBody>
                            <form onSubmit={handleSubmit}>
                                <MDBInput className='mb-4' type='email' id='form2Example1' label='Email address' onChange={e => setEmail(e.target.value)} />
                                <MDBInput className='mb-4' type='password' id='form2Example2' label='Password' onChange={e => setPassword(e.target.value)} />

                                <MDBRow className='mb-4'>
                                    <MDBCol className='d-flex justify-content-center'>
                                        <MDBCheckbox id='form2Example3' label='Remember me' defaultChecked />
                                    </MDBCol>
                                    <MDBCol>
                                        <a href='#!'>Forgot password?</a>
                                    </MDBCol>
                                </MDBRow>

                                <MDBBtn type='submit' className='mb-4' block>
                                    Sign in
                                </MDBBtn>

                                <div className='text-center'>
                                    <p>
                                        Not a member? <a href='signup'>Register</a>
                                    </p>
                                    <p>or sign up with:</p>

                                    <MDBBtn floating color="secondary" className='mx-1'>
                                        <MDBIcon fab icon='facebook-f' />
                                    </MDBBtn>

                                    <MDBBtn floating color="secondary" className='mx-1'>
                                        <MDBIcon fab icon='google' />
                                    </MDBBtn>

                                    <MDBBtn floating color="secondary" className='mx-1'>
                                        <MDBIcon fab icon='twitter' />
                                    </MDBBtn>

                                    <MDBBtn floating color="secondary" className='mx-1'>
                                        <MDBIcon fab icon='github' />
                                    </MDBBtn>
                                </div>
                            </form>

                        </MDBCardBody>

                    </MDBRow>

                </MDBCard>
            </MDBContainer>
        </div>
    );
};

export default SigninForm;