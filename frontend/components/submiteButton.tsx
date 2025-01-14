"use client";

import { useRouter } from "next/navigation";

interface SubmitButtonProps {
    onClick?: () => Promise<void> | void;
    targetRoute?: string;
    disabled?: boolean;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({
                                                       onClick,
                                                       targetRoute = "/movies",
                                                       disabled = false,
                                                   }) => {
    const router = useRouter();

    const handleSubmit = async () => {
        if (onClick) {
            await onClick();
        }
        router.push(targetRoute);
    };

    return (
        <button
            style={{
                width: "100%",
                padding: "10px",
                backgroundColor: disabled ? "#ccc" : "#007bff",
                color: "#fff",
                border: "none",
                borderRadius: "4px",
                cursor: disabled ? "not-allowed" : "pointer",
            }}
            onClick={handleSubmit}
            disabled={disabled}
        >
            {disabled ? "Processing..." : "Submit"}
        </button>
    );
};

export default SubmitButton;
