import React from 'react';
import '../styles/Dashboard.css';

function Dashboard() {
  return (
    <div className="dashboard-container">
      <h2>Employee Dashboard</h2>
      <div className="dashboard-content">
        <p>Welcome to the employee dashboard. Here you can manage accounts, view transactions, and more.</p>
        {/* Additional dashboard features can be added here */}
      </div>
    </div>
  );
}

export default Dashboard;
