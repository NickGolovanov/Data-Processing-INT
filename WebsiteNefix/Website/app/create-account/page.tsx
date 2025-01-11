import React from 'react';
import RegisterBox from "@/components/regester";
import Logo from "@/components/logo";

const Page: React.FC = () => {
    return (
        <div
            style={{display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '50px'}}
        >
            <Logo/>
            <RegisterBox/>
        </div>
    );
};

export default Page;
