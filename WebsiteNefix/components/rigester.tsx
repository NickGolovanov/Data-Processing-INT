import React from 'react';
import {Italiana} from 'next/font/google';
import styles from '../styles/RegisterInput.module.css';
import SubmitButton from "@/components/submiteButton";
import Link from "next/link";

const italiana = Italiana({subsets: ['latin'], weight: '400'});

const RegisterBox: React.FC = () => {
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
                style={{marginBottom: '20px', textAlign: 'center', fontSize: "30px"}}>Login</h2>

            <input
                type="text"
                placeholder="Username"
                style={{color: '#000'}}
                className={styles.registerInput}
            />

            <input
                type="text"
                placeholder="Email"
                style={{color: '#000'}}
                className={styles.registerInput}
            />


            <input
                type="password"
                placeholder="Password"
                style={{color: '#000'}}
                className={styles.registerInput}
            />

            <input
                type="password"
                placeholder="Repeat Password"
                style={{color: '#000'}}
                className={styles.registerInput}
            />

            <SubmitButton/>

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
