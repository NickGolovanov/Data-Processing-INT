'use client';

import React, { useState } from 'react';
import { Italiana } from 'next/font/google';
import styles from '../styles/RegisterInput.module.css';
import Link from "next/link";

const italiana = Italiana({ subsets: ['latin'], weight: '400' });

const LoginBox: React.FC = () => {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [successMessage, setSuccessMessage] = useState<string>('');

    const handleLogin = async (e: React.FormEvent): Promise<void> => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const data = await response.json();
                const token: string = data.data.token;

                localStorage.setItem('authToken', token);

                setSuccessMessage('Login successful! Redirecting...');
                setErrorMessage('');
                setTimeout(() => {
                    window.location.href = '/users'; // Change to your post-login page
                }, 1500);
            } else {
                const errorData = await response.json();
                setErrorMessage(errorData.message || 'Invalid email or password.');
            }
        } catch (error) {
            setErrorMessage('An error occurred. Please try again.');
        }
    };

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                padding: '20px',
                width: '350px',
                backgroundColor: '#363636',
                borderRadius: '30px',
            }}
        >
            <h2 className={italiana.className}
                style={{ marginBottom: '20px', textAlign: 'center', fontSize: "30px" }}>Login</h2>

            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}

            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={{ color: '#000' }}
                    className={styles.registerInput}
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={{ color: '#000' }}
                    className={styles.registerInput}
                />

                <button
                    type="submit"
                    style={{
                        marginTop: '20px',
                        padding: '10px 20px',
                        fontSize: '16px',
                        backgroundColor: '#007bff',
                        color: '#fff',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer',
                        width: '100%',
                    }}
                >
                    Login
                </button>
            </form>

            <Link
                href="/register"
                style={{
                    marginTop: '10px',
                    fontSize: '14px',
                    color: '#007bff',
                    textDecoration: 'underline',
                    cursor: 'pointer',
                }}
            >
                Create Account
            </Link>
        </div>
    );
};

export default LoginBox;
