"use client";

import React from "react";

interface DeleteAccountButtonProps {
    accountId: number; // The ID of the account to delete
    onDelete?: (accountId: number) => void; // Optional callback to handle state updates
}

const DeleteAccountButton: React.FC<DeleteAccountButtonProps> = ({
                                                                     accountId,
                                                                     onDelete,
                                                                 }) => {
    const handleDelete = async () => {
        if (!confirm("Are you sure you want to delete this account?")) {
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/v1/account/${accountId}`, {
                method: "DELETE",
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("authToken")}`,
                },
            });

            if (!response.ok) {
                throw new Error(`Failed to delete account: ${response.status}`);
            }

            alert("Account deleted successfully!");

            // Invoke the optional callback to update state
            if (onDelete) {
                onDelete(accountId);
            }
        } catch (err: any) {
            alert(`Error: ${err.message}`);
        }
    };

    return (
        <button
            onClick={handleDelete}
            style={{
                padding: "10px 20px",
                backgroundColor: "#dc3545", // Red background color
                color: "#fff", // White text
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

export default DeleteAccountButton;
