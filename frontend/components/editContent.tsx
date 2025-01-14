"use client";

import { useRouter } from "next/navigation";

interface EditButtonProps {
    type: string;
    id: string | number;
}

const EditButton: React.FC<EditButtonProps> = ({ type, id }) => {
    const router = useRouter();

    const handleSubmit = () => {
        router.push(`/${type}/${id}/edit/`); // Navigate to the edit page for the given ID
    };

    return (
        <button
            style={{
                width: "100%",
                padding: "10px",
                backgroundColor: "#007bff",
                color: "#fff",
                border: "none",
                borderRadius: "4px",
                cursor: "pointer",
            }}
            onClick={handleSubmit}
        >
            Edit
        </button>
    );
};

export default EditButton;
