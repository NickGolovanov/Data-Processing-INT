import React from 'react';
import { Italiana } from 'next/font/google';
import Link from 'next/link';


const italiana = Italiana({ subsets: ['latin'], weight: '400' });

const Menu: React.FC = () => {
    return (
        <nav
            style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                gap: '40px',
                color: 'white',
                marginTop: '-25px',
                textDecoration: 'none',
                fontSize: '35px',
            }}
        >
            <Link
                href="/series"
                className={italiana.className}
                style={{
                }}
            >
                Series
            </Link>

            <Link
                href="/users"
                className={italiana.className}
                style={{
                    marginTop: '10px',
                }}
            >
                Users
            </Link>

            <Link
                href="/movies"
                className={italiana.className}
                style={{
                }}
            >
                Movies
            </Link>
        </nav>
    );
};

export default Menu;
