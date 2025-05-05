import React from 'react';
import '../styles/CustomerLogin.css';

function CustomerLogin() {
  return (
    <div className="page-container">
      <div className="form-card">
        <h2>Customer Login</h2>
        <form>
          <input type="text" placeholder="Username" required />
          <input type="password" placeholder="Password" required />
          <button type="submit" className="bank-button primary">Login</button>
        </form>
      </div>
    </div>
  );
}

export default CustomerLogin;
