import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';

function CustomerDashboard() {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <h2>Customer Dashboard</h2>
      <div className="button-grid">
        <button onClick={() => navigate('/create-bank-account')}>Create Bank Account</button>
        <button onClick={() => navigate('/deposit')}>Deposit</button>
        <button onClick={() => navigate('/withdraw')}>Withdraw</button>
        <button onClick={() => navigate('/view-balance')}>View Balance</button>
        <button onClick={() => navigate('/view-history')}>View History</button>
        <button onClick={() => navigate('/view-accounts')}>View All Accounts</button>
        <button onClick={() => navigate('/transfer-own')}>Transfer Between Own Accounts</button>
        <button onClick={() => navigate('/transfer-other')}>Transfer to Another Customer</button>
        <button onClick={() => navigate('/delete-account')}>Delete Account</button>
        <button onClick={() => navigate('/')}>Logout</button>
      </div>
    </div>
  );
}

export default CustomerDashboard;
