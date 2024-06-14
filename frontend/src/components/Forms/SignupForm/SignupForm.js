import React, { useState } from 'react';
import './SignupForm.css';
import {
    MDBBtn,
    MDBInput,
    MDBIcon,
    MDBRow,
    MDBCol,
    MDBCheckbox,
    MDBContainer,
    MDBCard,
    MDBCardBody
} from 'mdb-react-ui-kit';

const SignupForm = ({ signup }) => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(username, email, password);
        signup(username, email, password);
    };

    return (
        <div className='container'>
            <MDBContainer className='my-5'>
                <MDBCard>
                    <MDBRow className='g-0 d-flex align-items-center'>
                        <MDBCardBody>
                            <form onSubmit={handleSubmit}>
                                <MDBRow className='mb-4'>
                                    <MDBCol>
                                        <MDBInput id='form3Example1' label='First name' />
                                    </MDBCol>
                                    <MDBCol>
                                        <MDBInput id='form3Example2' label='Last name' />
                                    </MDBCol>
                                </MDBRow>
                                <MDBInput className='mb-4' type='email' id='form3Example3' label='Email address' onChange={e => setUsername(e.target.value)} />
                                <MDBInput className='mb-4' type='password' id='form3Example4' label='Password' onChange={e => setPassword(e.target.value)} />

                                <MDBCheckbox
                                    wrapperClass='d-flex justify-content-center mb-4'
                                    id='form3Example5'
                                    label='Subscribe to our newsletter'
                                    defaultChecked
                                />

                                <MDBBtn type='submit' className='mb-4' block>
                                    Sign up
                                </MDBBtn>

                                <div className='text-center'>
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

export default SignupForm;