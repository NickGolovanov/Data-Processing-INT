"use client";

import React from "react";
import {useRouter} from "next/navigation";

interface AddButtonProps {
    type: string;
}

const AddButton: React.FC<AddButtonProps> = ({type}) => {
    const router = useRouter();

    const handleNavigation = () => {
        router.push(`/${type}/add`);
    };

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                borderRadius: '8px',
                padding: '20px',
                width: '200px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                color: '#000',
            }}
        >

            <button
                onClick={handleNavigation}
                style={{
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    width: "50px",
                    height: "50px",
                    borderRadius: "50%",
                    backgroundColor: "#007bff",
                    color: "#fff",
                    fontSize: "24px",
                    border: "none",
                    cursor: "pointer",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
                }}
            >
                +
            </button>
        </div>
    );
};

export default AddButton;
