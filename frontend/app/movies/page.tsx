"use client";

import React, {useEffect, useState} from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import ContentDisplay from "@/components/contentDisplay";
import AddButton from "@/components/addButton";

interface Movie {
    id: number;
    title: string;
}

const MoviesPage: React.FC = () => {
    const [movies, setMovies] = useState<Movie[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/v1/movie/general", {
                    method: "GET",
                    headers: {Authorization: "Bearer " + localStorage.getItem("authToken")}
                });                const data = await response.json();
                setMovies(data.slice(0, 100));
            } catch (err: unknown) {
                if (err instanceof Error)
                    setError(err.message);
            }
        };
        fetchMovies();
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }
    return (
        <div
            style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}
        >
            <Logo/>
            <Menu/>
            <div
                style={{
                    display: 'flex',
                    flexDirection: 'row',
                    gap: '50px',
                    marginTop: '30px',
                    width: '80%',
                    flexWrap: 'wrap',
                }}
            >
                {movies.map((movie) => (
                    <ContentDisplay
                        key={movie.id}
                        imageSrc="/images/Drive.jpg"
                        title={movie.title}
                        id={movie.id}
                        type="movies"
                    />
                ))}
                <AddButton type={"movies"} />
            </div>
        </div>
    );
};

export default MoviesPage;
