'use client';

import React, { useState } from 'react';
import { Italiana } from 'next/font/google';
import styles from '../styles/RegisterInput.module.css';
import SubmitButton from "@/components/submiteButton";
import Link from "next/link";

const italiana = Italiana({ subsets: ['latin'], weight: '400' });

interface FormData {
    email: string;
    password: string;
    confirmPassword: string;
}

const RegisterBox: React.FC = () => {
    const [formData, setFormData] = useState<FormData>({
        email: '',
        password: '',
        confirmPassword: '',
    });

    const [errorMessage, setErrorMessage] = useState<string>('');
    const [successMessage, setSuccessMessage] = useState<string>('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent): Promise<void> => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            setErrorMessage('Passwords do not match!');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: formData.email,
                    password: formData.password,
                }),
            });

            if (response.ok) {
                const responseData = await response.json();
                const token: string = responseData.token;

                // Store the token in localStorage
                localStorage.setItem('authToken', token);

                setSuccessMessage('Registration successful! You can now log in.');
                setErrorMessage('');
                setFormData({  email: '', password: '', confirmPassword: '' });
            } else {
                const errorData = await response.json();
                setErrorMessage(errorData.message || 'Registration failed.');
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
                style={{ marginBottom: '20px', textAlign: 'center', fontSize: "30px" }}>Register</h2>

            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="email"
                    value={formData.email}
                    placeholder="Email"
                    onChange={handleChange}
                    style={{ color: '#000' }}
                    className={styles.registerInput}
                />

                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    placeholder="Password"
                    onChange={handleChange}
                    style={{ color: '#000' }}
                    className={styles.registerInput}
                />

                <input
                    type="password"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    placeholder="Repeat Password"
                    onChange={handleChange}
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
                    Submit
                </button>
            </form>

            <Link
                href="/"
                style={{
                    marginTop: '10px',
                    fontSize: '14px',
                    color: '#007bff',
                    textDecoration: 'underline',
                    cursor: 'pointer',
                }}
            >
                Login
            </Link>
        </div>
    );
};

export default RegisterBox;
