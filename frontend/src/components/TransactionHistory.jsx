import React from 'react';
import '../styles/TransactionHistory.css';

function TransactionHistory() {
  // Placeholder data; replace with actual transaction history
  const transactions = [
    { date: '2025-04-01', description: 'Deposit', amount: '$1,000.00' },
    { date: '2025-04-05', description: 'Withdrawal', amount: '$200.00' },
    { date: '2025-04-10', description: 'Payment', amount: '$150.00' },
  ];

  return (
    <div className="transaction-history-container">
      <h2>Transaction History</h2>
      <table className="transaction-table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((txn, index) => (
            <tr key={index}>
              <td>{txn.date}</td>
              <td>{txn.description}</td>
              <td>{txn.amount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TransactionHistory;
