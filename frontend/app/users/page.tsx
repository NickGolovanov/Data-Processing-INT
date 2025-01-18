"use client";

import React, { useEffect, useState } from "react";
import Logo from "@/components/logo";
import Menu from "@/components/menu";

interface Account {
    accountId: number;
    email: string;
    paymentMethod: string;
    referralDiscount: { referralDiscountId: number; link: string } | null;
    subscriptions: { subscriptionId: number; type: string; startDate: string; endDate: string }[];
    isBlocked?: boolean; // Added optional field for blocked status
}

const isBlocked = async (accountId: number) => {
    try {
        const response = await fetch(`http://localhost:8080/api/v1/account/${accountId}/block`, {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("authToken"),
            },
        });
        if (!response.ok) {
            throw new Error(`Failed to fetch account blocked status: ${response.status}`);
        }
        const data = await response.json();
        return data.data;
    } catch (err: unknown) {
        console.error(err);
        return false;
    }
};

const UserPage: React.FC = () => {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/v1/account", {
                    method: "GET",
                    headers: { Authorization: "Bearer " + localStorage.getItem("authToken") },
                });
                if (!response.ok) {
                    throw new Error(`Failed to fetch accounts: ${response.status}`);
                }
                const data = await response.json();
                const accountsData: Account[] = data.data;

                // Fetch blocked status for each account
                const updatedAccounts = await Promise.all(
                    accountsData.map(async (account) => ({
                        ...account,
                        isBlocked: await isBlocked(account.accountId),
                    }))
                );

                setAccounts(updatedAccounts);
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
                <tr style={{ backgroundColor: "#007bff", color: "#fff" }}>
                    <th style={cellStyle}>Account ID</th>
                    <th style={cellStyle}>Email</th>
                    <th style={cellStyle}>Payment Method</th>
                    <th style={cellStyle}>Referral Discount</th>
                    <th style={cellStyle}>Account Blocked</th>
                    <th style={cellStyle}>Delete account</th>
                </tr>
                </thead>
                <tbody>
                {accounts.map((account) => (
                    <tr key={account.accountId}>
                        <td style={cellStyle}>{account.accountId}</td>
                        <td style={cellStyle}>{account.email}</td>
                        <td style={cellStyle}>{account.paymentMethod}</td>
                        <td style={cellStyle}>
                            {account.referralDiscount ? "Yes" : "No"}
                        </td>
                        <td style={cellStyle}>
                            {account.isBlocked !== undefined ? (
                                account.isBlocked ? (
                                    "Yes"
                                ) : (
                                    "No"
                                )
                            ) : (
                                "Loading..."
                            )}
                        </td>


                        <td style={cellStyle}>
                            <button
                                style={{
                                    padding: "10px 20px",
                                    backgroundColor: "#dc3545",
                                    color: "#fff",
                                    border: "none",
                                    borderRadius: "4px",
                                    cursor: "pointer",
                                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                                    transition: "background-color 0.3s ease",
                                }}
                                onMouseOver={(e) =>
                                    (e.currentTarget.style.backgroundColor = "#c82333")
                                }
                                onMouseOut={(e) =>
                                    (e.currentTarget.style.backgroundColor = "#dc3545")
                                }
                                onClick={async () => {
                                    try {
                                        const response = await fetch(
                                            `http://localhost:8080/api/v1/account/${account.accountId}`,
                                            {
                                                method: "DELETE",
                                                headers: {
                                                    Authorization:
                                                        "Bearer " +
                                                        localStorage.getItem("authToken"),
                                                },
                                            }
                                        );
                                        if (!response.ok) {
                                            throw new Error(
                                                `Failed to delete account: ${response.status}`
                                            );
                                        }
                                        setAccounts(
                                            accounts.filter(
                                                (acc) => acc.accountId !== account.accountId
                                            )
                                        );
                                    } catch (err: any) {
                                        setError(err.message);
                                    }
                                }}
                            >
                                Delete
                            </button>
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
