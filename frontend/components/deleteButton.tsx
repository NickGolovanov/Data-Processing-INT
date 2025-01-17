"use client";

import React from "react";
import {useRouter} from "next/navigation"; // Use `next/navigation` for App Router

interface DeleteButtonProps {
    id: number; // The ID of the item to delete
    type: string; // The type of the item (e.g., "account", "movie", "series")
    onDelete?: (id: number) => void; // Optional callback to handle successful deletion
}

const DeleteButton: React.FC<DeleteButtonProps> = ({id, type, onDelete}) => {
    const router = useRouter();

    const handleDelete = async () => {
        if (!confirm(`Are you sure you want to delete this ${type}?`)) {
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/v1/${type}/${id}`, {
                method: "DELETE",
                headers: {

                    Authorization: "Bearer " + localStorage.getItem("authToken")
                }
            });

            if (!response.ok) {
                throw new Error(`Failed to delete ${type}: ${response.status}`);
            }

            alert(`${type.charAt(0).toUpperCase() + type.slice(1)} deleted successfully!`);

            if (onDelete) {
                onDelete(id);
            }
            if (type === "movie") {
                router.push(`/movies`);
            } else if (type === "series") {
                router.push(`/series`);
            } else {
                router.push(`/${type}`);
            }

        } catch (err: unknown) {
            if (err instanceof Error) {
                alert(`Error: ${err.message}`);
            } else {
                alert("An unknown error occurred");
            }
        }
    };

    return (
        <button
            onClick={handleDelete}
            style={{
                padding: "10px 20px",
                backgroundColor: "#dc3545",
                color: "#fff",
                border: "none",
                borderRadius: "4px",
                cursor: "pointer",
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
            }}
        >
            Delete
        </button>
    );
};

export default DeleteButton;
