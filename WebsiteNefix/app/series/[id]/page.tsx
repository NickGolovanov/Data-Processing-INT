"use client";

import React, {useEffect, useState} from "react";
import {use} from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";
import EditButton from "@/components/editContent";
import Image from "next/image";

interface Series {
    seriesId: number;
    title: string;
    views: number;
    minimumAge: number;
    seasons: { seasonId: number; name: string }[]; // Simplified season details
    infoSeries: { id: number; info: string }[]; // Simplified additional info
}

const SeriesPage = ({params}: { params: Promise<{ id: string }> }) => {
    // Unwrap the params Promise
    const {id} = use(params);

    const [series, setSeries] = useState<Series | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchSeries = async () => {
            try {
                const response = await fetch(`http://localhost:8080/series/${id}`, {
                    method: "GET",
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("authToken")
                    }
                });
                if (!response.ok) {
                    throw new Error(`Failed to fetch: ${response.status}`);
                }
                const data = await response.json();
                setSeries(data);
            } catch (err: unknown) {
                if (err instanceof Error) {
                    setError(err.message);
                } else {
                    setError("An unknown error occurred");
                }
            }
        };

        fetchSeries();
    }, [id]);

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
            <Logo/>
            <Menu/>
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


                <div className="flex flex-col justify-between items-start p-5 shadow-md rounded-lg w-1/2 ml-10">
                    <h1 style={{fontSize: "30px"}}>Title: {series.title}</h1>
                    <p>Views: {series.views}</p>
                    <p>Minimum Age: {series.minimumAge}</p>

                    {/*<h2>Seasons:</h2>*/}
                    {/*<ul>*/}
                    {/*    {series.seasons.map((season) => (*/}
                    {/*        <li key={season.seasonId}>{season.name}</li>*/}
                    {/*    ))}*/}
                    {/*</ul>*/}

                    {/*<h2>Additional Information:</h2>*/}
                    {/*<ul>*/}
                    {/*    {series.infoSeries.map((info) => (*/}
                    {/*        <li key={info.id}>{info.info}</li>*/}
                    {/*    ))}*/}
                    {/*</ul>*/}

                    <EditButton type={"series"} id={id}/>
                </div>
            </div>
        </div>
    );
};

export default SeriesPage;
