"use client";

import React, { useEffect, useState } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";

interface Account {
    accountId: number;
    email: string;
    paymentMethod: string;
    profiles: { id: number; name: string }[];
}

const UserPage: React.FC = () => {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await fetch("http://localhost:8080/account");
                if (!response.ok) {
                    throw new Error(`Failed to fetch accounts: ${response.status}`);
                }
                const data = await response.json();
                console.log(data);
                setAccounts(data);
            } catch (err: any) {
                setError(err.message);
            }
        };

        fetchAccounts();
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div
            style={{
                padding: "20px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
            }}
        >
            <Logo/>
            <Menu/>
            <h1>User Accounts</h1>
            <table
                style={{
                    width: "90%",
                    borderCollapse: "collapse",
                    marginTop: "20px",
                }}
            >
                <thead>
                <tr style={{ backgroundColor: "#007bff", color: "#fff" }}>
                    <th style={cellStyle}>Account ID</th>
                    <th style={cellStyle}>Email</th>
                    <th style={cellStyle}>Payment Method</th>
                    <th style={cellStyle}>Referral Discount</th>
                    <th style={cellStyle}>Subscriptions</th>
                    <th style={cellStyle}>Profiles</th>
                    <th style={cellStyle}>Blocked Accounts</th>
                </tr>
                </thead>
                <tbody>
                {accounts.map((account) => (
                    <tr key={account.accountId}>
                        <td style={cellStyle}>{account.accountId}</td>
                        <td style={cellStyle}>{account.email}</td>
                        <td style={cellStyle}>{account.paymentMethod}</td>
                        <td style={cellStyle}>
                            {account.referralDiscount
                                ? `${account.referralDiscount.amount}%`
                                : "None"}
                        </td>
                        <td style={cellStyle}>
                            {account.subscriptions.length > 0
                                ? account.subscriptions.map((sub) => sub.name).join(", ")
                                : "None"}
                        </td>
                        <td style={cellStyle}>
                            {account.profiles.length > 0
                                ? account.profiles.map((profile) => profile.name).join(", ")
                                : "None"}
                        </td>
                        <td style={cellStyle}>
                            {account.blockedAccounts.length > 0
                                ? account.blockedAccounts
                                    .map((blocked) => blocked.reason)
                                    .join(", ")
                                : "None"}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

const cellStyle: React.CSSProperties = {
    border: "1px solid #ddd",
    padding: "8px",
    textAlign: "center",
};

export default UserPage;
