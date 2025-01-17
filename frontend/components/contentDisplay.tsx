import React from 'react';
import Image from "next/image";


interface ContentDisplayProps {
    imageSrc: string;
    title: string;
    id: number;
    type: string;
}

const ContentDisplay: React.FC<ContentDisplayProps> = ({ imageSrc, title, id, type }) => {
    return (
        <div>
            <a
                href={`/${type}/${id}`}
                style={{
                    textDecoration: 'none',
                    color: 'inherit',
                }}
            >
                <div
                    style={{
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        justifyContent: 'center',
                        border: '1px solid #ccc',
                        borderRadius: '8px',
                        padding: '20px',
                        width: '200px',
                        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                        backgroundColor: '#fff',
                        color: '#000',
                    }}
                >
                    <Image
                        src={imageSrc}
                        alt={title}
                        width={800}
                        height={600}
                        style={{
                            borderRadius: '8px',
                        }}
                    />
                    <h3
                        // className={italiana.className}
                        style={{
                            marginTop: '5px',
                            fontSize: 'auto',
                            textAlign: 'center',
                        }}
                    >
                        {title}
                    </h3>
                </div>
            </a>
        </div>
    );
};

export default ContentDisplay;
