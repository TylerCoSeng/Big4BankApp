import React from 'react';
import '../styles/EmployeeLogin.css';

function EmployeeLogin() {
  return (
    <div className="page-container">
      <div className="form-card">
        <h2>Employee Login</h2>
        <form>
          <input type="text" placeholder="Employee ID" required />
          <input type="password" placeholder="Password" required />
          <button type="submit" className="bank-button primary">Login</button>
        </form>
      </div>
    </div>
  );
}

export default EmployeeLogin;
