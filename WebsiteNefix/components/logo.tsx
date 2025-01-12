import React from 'react';
import {Italiana} from 'next/font/google';

const italiana = Italiana({subsets: ['latin'], weight: '400'});

const Logo: React.FC = () => {
    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'center',
            }}
        >
            <p className={italiana.className} style={{ fontSize: '80px' }}>
                NEFIX
            </p>
        </div>
    );
};

export default Logo;
