import React, { useState, useEffect } from 'react';
import './SubscriptionPage.css';
import TopBar from '../../../components/TopBar/TopBar';
import { useApi } from '../../../hooks/useApi';
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn } from 'mdb-react-ui-kit';

const SubscriptionPage = () => {
    const [userSubscription, setUserSubscription] = useState(null);
    const { fetchApiData, postApiData } = useApi();

    useEffect(() => {
        fetchSubscriptions();
    }, []);

    const fetchSubscriptions = async () => {
        try {
            const results = await fetchApiData('api/v1/user_subscriptions');
            const rateLimitSubscription = results.data.data.filter(element => element.subscription.subscription_type === "RATE_LIMIT")[0];
            if (rateLimitSubscription) {
                console.log(rateLimitSubscription);
                setUserSubscription(rateLimitSubscription);
            }
        } catch (exception) {
            console.log(exception);
        }
    }

    const handleSubscribe = (plan) => {
        console.log(`Subscribed to the ${plan} plan.`);
    };

    const calculateRemainingDays = (expiryDate) => {
        const now = new Date();
        const expiry = new Date(expiryDate);
        const timeDiff = expiry - now;
        const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
        return daysDiff > 0 ? daysDiff : 0;
    };

    return (
        <div>
            <TopBar />
            <div className="d-grid gap-3">
                <MDBContainer breakpoint="lg" className="">
                    <h2 className="text-center mb-4">Choose Your Subscription Plan</h2>
                    {userSubscription && (
                        <div className="text-center mb-4">
                            <p>Your current plan: <strong>{userSubscription.plan}</strong></p>
                            <p>Days remaining: <strong>{calculateRemainingDays(userSubscription.expiryDate)}</strong> days</p>
                        </div>
                    )}
                    {!userSubscription && (
                        <MDBRow>
                            <MDBCol md="4" className="mb-4">
                                <MDBCard>
                                    <MDBCardBody>
                                        <MDBCardTitle>Basic Plan</MDBCardTitle>
                                        <MDBCardText>
                                            <p>$10/month</p>
                                            <p>Access to basic features</p>
                                        </MDBCardText>
                                        <MDBBtn onClick={() => handleSubscribe('Basic')}>Subscribe</MDBBtn>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                            <MDBCol md="4" className="mb-4">
                                <MDBCard>
                                    <MDBCardBody>
                                        <MDBCardTitle>Standard Plan</MDBCardTitle>
                                        <MDBCardText>
                                            <p>$20/month</p>
                                            <p>Access to standard features</p>
                                        </MDBCardText>
                                        <MDBBtn onClick={() => handleSubscribe('Standard')}>Subscribe</MDBBtn>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                            <MDBCol md="4" className="mb-4">
                                <MDBCard>
                                    <MDBCardBody>
                                        <MDBCardTitle>Premium Plan</MDBCardTitle>
                                        <MDBCardText>
                                            <p>$30/month</p>
                                            <p>Access to all features</p>
                                        </MDBCardText>
                                        <MDBBtn onClick={() => handleSubscribe('Premium')}>Subscribe</MDBBtn>
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                        </MDBRow>)}
                </MDBContainer>
            </div>
        </div>
    );
};

export default SubscriptionPage;
