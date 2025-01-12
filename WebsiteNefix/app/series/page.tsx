"use client";

import React, {useEffect, useState} from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import ContentDisplay from "@/components/contentDisplay";
import AddButton from "@/components/addButton";

interface Series {
    seriesId: number;
    title: string;
}

const SeriesPage: React.FC = () => {
    const [series, setseries] = useState<Series[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await fetch("http://localhost:8080/series");
                const data = await response.json();
                setseries(data.slice(0, 100));
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
                {series.map((series) => (
                    <ContentDisplay
                        key={series.seriesId}
                        imageSrc="/images/Peppe.jpg"
                        title={series.title}
                        id={series.seriesId}
                        type="series"
                    />
                ))}

                <AddButton type={"series"} />
            </div>
        </div>
    );
};

export default SeriesPage;
