"use client";

import React, { useEffect, useState } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";

interface Account {
    accountId: number;
    email: string;
    paymentMethod: string;
    referralDiscount: {referralDiscountId: number, link: string};
    subscriptions: {subscriptionId: number, type: string, startDate: string, endDate: string}[];
}

const isBlocked = async (accountId: number) => {
    try {
        const response = await fetch(`http://localhost:8080/account/${accountId}/is-blocked`);
        if (!response.ok) {
            throw new Error(`Failed to fetch account status: ${response.status}`);
        }
        const data: { isBlocked: boolean } = await response.json();
        return data;
    } catch (err: unknown) {
        if (err instanceof Error) {
            console.error(err.message);
        }
    }
};

const UserPage: React.FC = () => {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [blockedStatus, setBlockedStatus] = useState<{ [key: number]: boolean }>({});
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await fetch("http://localhost:8080/account");
                if (!response.ok) {
                    throw new Error(`Failed to fetch accounts: ${response.status}`);
                }
                const data: Account[] = await response.json();
                setAccounts(data);
                console.log(data)
            } catch (err: any) {
                console.error(err.message);
                setError(err.message);
            }
        };

        fetchAccounts();
    }, []);

    useEffect(() => {
        const fetchBlockedStatus = async () => {
            const statuses: { [key: number]: boolean } = {};
            setBlockedStatus(statuses);
        };

        if (accounts.length > 0) {
            fetchBlockedStatus();
        }
    }, [accounts]);

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
            <Logo />
            <Menu />
            <h1>User Accounts</h1>
            <table
                style={{
                    width: "90%",
                    borderCollapse: "collapse",
                    marginTop: "20px",
                }}
            >
                <thead>
                <tr style={{backgroundColor: "#007bff", color: "#fff"}}>
                    <th style={cellStyle}>Account ID</th>
                    <th style={cellStyle}>Email</th>
                    <th style={cellStyle}>Payment Method</th>
                    <th style={cellStyle}>Referral Discount</th>
                    <th style={cellStyle}>Subscription Type</th>
                    <th style={cellStyle}>Starts</th>
                    <th style={cellStyle}>Ends</th>
                    <th style={cellStyle}>Profiles</th>
                    <th style={cellStyle}>Account Blocked</th>
                </tr>
                </thead>
                <tbody>
                {accounts.map((account) => (
                    <tr key={account.accountId}>
                        <td style={cellStyle}>{account.accountId}</td>
                        <td style={cellStyle}>{account.email}</td>
                        <td style={cellStyle}>{account.paymentMethod}</td>
                        <td style={cellStyle}>{account.referralDiscount == null ? "No" : "Yes"}</td>
                        <td style={cellStyle}>Placeholder</td>
                        <td style={cellStyle}>Placeholder</td>
                        <td style={cellStyle}>Placeholder</td>
                        <td style={cellStyle}>Placeholder</td>
                        <td style={cellStyle}>
                            {blockedStatus[account.accountId] ? "Yes" : "No"}
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
