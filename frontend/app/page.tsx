import React from 'react';
import Logo from '../components/logo';
import Login from "@/components/login";

const Home: React.FC = () => {
    return (
        <div
            style={{display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '50px'}}
        >
            <Logo/>
            <Login/>
        </div>

    );
};

export default Home;
