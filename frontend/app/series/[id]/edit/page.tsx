"use client";

import React, {use, useEffect, useState} from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import Image from "next/image";
import DeleteButton from "@/components/deleteButton";

interface Series {
    seriesId: number;
    title: string;
    views: number;
    minimumAge: number;
}

const SeriesEditPage = ({ params }: { params: Promise<{ id: string }> }) => {
    const { id } = use(params);

    const [series, setSeries] = useState<Series | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [formState, setFormState] = useState<Partial<Series>>({});

    useEffect(() => {
        const fetchSeries = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/series/${id}`, {
                    method: "GET",
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("authToken")
                    }

                });
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const data = await response.json();
                setSeries(data.data);
                setFormState(data.data); // Pre-fill the form state
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("An unknown error occurred");
                }            }
        };

        fetchSeries();
    }, [id]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormState((prev) => ({
            ...prev,
            [name]: name === "views" || name === "minimumAge" ? parseInt(value) : value, // Ensure numeric fields are parsed
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/v1/series/${id}`, {
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

            const updatedSeries = await response.json();
            setSeries(updatedSeries); // Update the state with the new series data
            alert("Series updated successfully!");
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

    if (!series) {
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
                {/* Series Image */}
                <Image
                    src="/images/Peppe.jpg"
                    alt={series.title}
                    width={300} // Provide a numeric value for width
                    height={400} // Provide a numeric value for height
                    style={{
                        objectFit: "cover",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                />

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
                    <h1>Edit Series</h1>
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
                        Save Changes
                    </button>
                    <DeleteButton id={parseInt(id)} type={"series"}/>
                </div>
            </form>
        </div>
    );
};

export default SeriesEditPage;
