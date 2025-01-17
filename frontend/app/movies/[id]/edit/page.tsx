"use client";

import React, { useEffect, useState } from "react";
import { use } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import Image from "next/image";
import DeleteButton from "@/components/deleteButton";

interface Movie {
    id: number;
    title: string;
    duration: number;
    SD: boolean;
    HD: boolean;
    UHD: boolean;
    views: number;
}

const MovieEditPage = ({ params }: { params: Promise<{ id: string }> }) => {
    const { id } = use(params);

    const [movie, setMovie] = useState<Movie | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [formState, setFormState] = useState<Partial<Movie>>({});

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/movie/${id}`, {
                    method: "GET",
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("authToken")
                    }
                });
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const data = await response.json();
                setMovie(data.data);
                setFormState(data.data);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("An unknown error occurred");
                }            }
        };

        fetchMovie();
    }, [id]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState((prev) => ({
            ...prev,
            [name]: value,
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
            const response = await fetch(`http://localhost:8080/api/v1//movie/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("authToken")
                },
                body: JSON.stringify(formState),
            });

            if (!response.ok) {
                throw new Error(`Failed to update: ${response.status}`);
            }

            const updatedMovie = await response.json();
            setMovie(updatedMovie.data);
            alert("Movie updated successfully!");
        } catch (err: unknown) {
            if (err instanceof Error) {
                setError(err.message);
            } else {
                setError("An unknown error occurred");
            }        }
    };

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (!movie) {
        return <div>Loading...</div>;
    }

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
                {/* Movie Image */}
                <Image
                    src="/images/Drive.jpg"
                    alt={movie.title}
                    width={300} // Provide a numeric value for width
                    height={400} // Provide a numeric value for height
                    style={{
                        objectFit: "cover",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                />

                {/* Editable Movie Info */}
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
                    <h1>Edit Movie</h1>
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
                        Save Changes
                    </button>
                    <DeleteButton id={ parseInt(id) } type={"movie"}/>
                </div>
            </form>
        </div>
    );
};

export default MovieEditPage;
