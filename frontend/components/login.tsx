"use client";

import React, { useState } from "react";
import { Italiana } from "next/font/google";
import styles from "../styles/RegisterInput.module.css";
import SubmitButton from "@/components/submiteButton";

const italiana = Italiana({ subsets: ["latin"], weight: "400" });

const LoginBox: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState<string | null>(null);

    const handleLogin = async () => {
        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error("Invalid email or password.");
            }

            const data = await response.json();

            // Handle success (e.g., save token, navigate, etc.)
            alert("Login successful!");
            console.log("User data:", data);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("An unknown error occurred.");
            }
        }
    };

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                padding: "20px",
                width: "350px",
                backgroundColor: "#363636",
                borderRadius: "30px",
            }}
        >
            <h2
                className={italiana.className}
                style={{ marginBottom: "20px", textAlign: "center", fontSize: "30px" }}
            >
                Login
            </h2>

            <input
                type="text"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                style={{ color: "#000" }}
                className={styles.registerInput}
            />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                style={{ color: "#000" }}
                className={styles.registerInput}
            />

            <SubmitButton onClick={handleLogin} />

            {error && <p style={{ color: "red", marginTop: "10px" }}>{error}</p>}

            <a
                href="/create-account"
                style={{
                    marginTop: "10px",
                    fontSize: "14px",
                    color: "#007bff",
                    textDecoration: "underline",
                    cursor: "pointer",
                }}
            >
                Create Account
            </a>
        </div>
    );
};

export default LoginBox;
