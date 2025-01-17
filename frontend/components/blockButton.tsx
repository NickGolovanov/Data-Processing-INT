import React from "react";

interface RedButtonProps {
    accountId: string;
    isBlocked: boolean;
    onToggleBlock: (accountId: string) => void;
}

const RedButton: React.FC<RedButtonProps> = ({ accountId, isBlocked, onToggleBlock }) => {
    return (
        <button
            style={{
                backgroundColor: "red",
                color: "white",
                border: "none",
                padding: "8px 16px",
                borderRadius: "4px",
                cursor: "pointer",
                fontWeight: "bold",
            }}
            onClick={() => onToggleBlock(accountId)}
        >
            {isBlocked ? "Unblock" : "Block"}
        </button>
    );
};

export default RedButton;
