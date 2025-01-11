"use client";

import React, {useEffect, useState} from "react";
import {use} from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import EditButton from "@/components/editContent";

interface Movie {
    id: number;
    title: string;
    duration: number;
    SD: boolean;
    HD: boolean;
    UHD: boolean;
    views: number;

}

const MoviePage = ({params}: { params: Promise<{ id: string }> }) => {
    // Unwrap the params Promise
    const {id} = use(params);

    const [movie, setMovie] = useState<Movie | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await fetch(`http://localhost:8080/movie/${id}`);
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const data = await response.json();
                setMovie(data);
            } catch (err: any) {
                setError(err.message);
            }
        };

        fetchMovie();
    }, [id]);

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
            <div
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
                <img
                    src="/images/Drive.jpg"
                    alt={movie.title}
                    style={{
                        width: "300px",
                        height: "400px",
                        objectFit: "cover",
                        borderRadius: "8px",
                        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    }}
                />

                {/* Movie Info */}
                <div className="flex flex-col justify-between items-start p-5 shadow-md rounded-lg w-1/2 ml-10">


                    <h1 style={{fontSize: "30px"}}>Title: {movie.title}</h1>
                    <p>Duration: {movie.duration} minutes</p>
                    <p>SD: {movie.SD ? "Yes" : "No"}</p>
                    <p>HD: {movie.HD ? "Yes" : "No"}</p>
                    <p>UHD: {movie.UHD ? "Yes" : "No"}</p>
                    <p>Views: {movie.views}</p>
                    <p>Id: {id}</p>
                    <EditButton type={"movies"} id={id}/>
                </div>
            </div>
        </div>
    );
};

export default MoviePage;
