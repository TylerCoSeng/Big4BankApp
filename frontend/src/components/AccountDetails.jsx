import React from 'react';
import '../styles/AccountDetails.css';

function AccountDetails() {
  // Placeholder data; replace with actual account details
  const account = {
    accountNumber: '123456789',
    accountType: 'Checking',
    balance: '$5,000.00',
  };

  return (
    <div className="account-details-container">
      <h2>Account Details</h2>
      <div className="account-info">
        <p><strong>Account Number:</strong> {account.accountNumber}</p>
        <p><strong>Account Type:</strong> {account.accountType}</p>
        <p><strong>Balance:</strong> {account.balance}</p>
      </div>
    </div>
  );
}

export default AccountDetails;
