"use client";

import { useRouter } from 'next/navigation';

const SubmitButton: React.FC = () => {
    const router = useRouter();

    const handleSubmit = () => {
        router.push('/movies'); // Replace with your target route
    };

    return (
        <button
            style={{
                width: '100%',
                padding: '10px',
                backgroundColor: '#007bff',
                color: '#fff',
                border: 'none',
                borderRadius: '4px',
                cursor: 'pointer',
            }}
            onClick={handleSubmit}
        >
            Submit
        </button>
    );
};

export default SubmitButton;
