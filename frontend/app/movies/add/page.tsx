"use client";

import React, { useState } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import Image from "next/image";

interface Movie {
    title: string;
    duration: number;
    SD: boolean;
    HD: boolean;
    UHD: boolean;
    views: number;
}

const MovieAddPage: React.FC = () => {
    const [formState, setFormState] = useState<Partial<Movie>>({
        title: "",
        duration: 0,
        SD: false,
        HD: false,
        UHD: false,
        views: 0,
    });
    const [error, setError] = useState<string | null>(null);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState((prev) => ({
            ...prev,
            [name]: name === "duration" || name === "views" ? parseInt(value) : value,
        }));
    };

    const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, checked } = e.target;
        setFormState((prev) => ({
            ...prev,
            [name]: checked,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/v1/movie`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("authToken")
                },
                body: JSON.stringify(formState),
            });

            if (!response.ok) {
                throw new Error(`Failed to add movie: ${response.status}`);
            }

            const newMovie = await response.json();
            alert("Movie added successfully!");
            console.log("New Movie:", newMovie);
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("An unknown error occurred");
            }
        }
    };

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
            }}
        >
            <Logo />
            <Menu />
            <form
                onSubmit={handleSubmit}
                style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-start",
                    justifyContent: "center",
                    marginTop: "30px",
                    gap: "50px",
                    width: "80%",
                }}
            >
                {/* Movie Image Placeholder */}
                <Image
                    src="/images/Drive.jpg"
                    alt="Add Movie"
                    width={300}
                    height={400}
                    style={{
                        objectFit: "cover",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                />

                {/* Add Movie Form */}
                <div
                    style={{
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        alignItems: "flex-start",
                        padding: "20px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                        borderRadius: "8px",
                        width: "50%",
                    }}
                >
                    <h1>Add Movie</h1>
                    <label>
                        Title:
                        <input
                            type="text"
                            name="title"
                            value={formState.title || ""}
                            onChange={handleInputChange}
                            style={{ width: "100%", padding: "5px", marginBottom: "10px", color: "#000" }}
                        />
                    </label>
                    <label>
                        Duration:
                        <input
                            type="number"
                            name="duration"
                            value={formState.duration || ""}
                            onChange={handleInputChange}
                            style={{ width: "100%", padding: "5px", marginBottom: "10px", color: "#000" }}
                        />
                    </label>
                    <label>
                        SD:
                        <input
                            type="checkbox"
                            name="SD"
                            checked={formState.SD || false}
                            onChange={handleCheckboxChange}
                        />
                    </label>
                    <label>
                        HD:
                        <input
                            type="checkbox"
                            name="HD"
                            checked={formState.HD || false}
                            onChange={handleCheckboxChange}
                        />
                    </label>
                    <label>
                        UHD:
                        <input
                            type="checkbox"
                            name="UHD"
                            checked={formState.UHD || false}
                            onChange={handleCheckboxChange}
                        />
                    </label>
                    <label>
                        Views:
                        <input
                            type="number"
                            name="views"
                            value={formState.views || ""}
                            onChange={handleInputChange}
                            style={{ width: "100%", padding: "5px", marginBottom: "10px", color: "#000" }}
                        />
                    </label>

                    <button
                        type="submit"
                        style={{
                            padding: "10px 20px",
                            backgroundColor: "#007bff",
                            color: "#fff",
                            border: "none",
                            borderRadius: "4px",
                            cursor: "pointer",
                            marginTop: "20px",
                        }}
                    >
                        Add Movie
                    </button>
                </div>
            </form>
        </div>
    );
};

export default MovieAddPage;
