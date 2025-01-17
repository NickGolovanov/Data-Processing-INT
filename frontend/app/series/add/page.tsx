"use client";

import React, { useState } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import Image from "next/image";

interface Series {
    title: string;
    views: number;
    minimumAge: number;
}

const SeriesAddPage: React.FC = () => {
    const [formState, setFormState] = useState<Partial<Series>>({
        title: "",
        views: 0,
        minimumAge: 0,
    });
    const [error, setError] = useState<string | null>(null);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState((prev) => ({
            ...prev,
            [name]: name === "views" || name === "minimumAge" ? parseInt(value) : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/v1/series`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("authToken")
                },
                body: JSON.stringify(formState),
            });

            if (!response.ok) {
                throw new Error(`Failed to add series: ${response.status}`);
            }

            const newSeries = await response.json();
            alert("Series added successfully!");
            console.log("New Series:", newSeries);
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
                {/* Series Image Placeholder */}
                <Image
                    src="/images/Peppe.jpg"
                    alt="Add Series"
                    width={300}
                    height={400}
                    style={{
                        objectFit: "cover",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                />

                {/* Add Series Form */}
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
                    <h1>Add Series</h1>
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
                        Views:
                        <input
                            type="number"
                            name="views"
                            value={formState.views || ""}
                            onChange={handleInputChange}
                            style={{ width: "100%", padding: "5px", marginBottom: "10px", color: "#000" }}
                        />
                    </label>
                    <label>
                        Minimum Age:
                        <input
                            type="number"
                            name="minimumAge"
                            value={formState.minimumAge || ""}
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
                        Add Series
                    </button>
                </div>
            </form>
        </div>
    );
};

export default SeriesAddPage;
