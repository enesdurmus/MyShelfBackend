import React, { createContext, useState } from 'react';

export const ErrorContext = createContext();

export const ErrorProvider = ({ children }) => {
    const [error, setError] = useState(null);

    const handleError = (errorMessage) => {
        setError(errorMessage);
        setTimeout(() => setError(null), 3000); // Clear error after 3 seconds
    };

    return (
        <ErrorContext.Provider value={{ error, handleError }}>
            {children}
        </ErrorContext.Provider>
    );
};
