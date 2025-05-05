import React from 'react';
import '../styles/SharedStyles.css';

function ResetPassword() {
  return (
    <div className="page-container">
      <div className="form-card">
        <h2>Reset Password</h2>
        <form>
          <input type="text" placeholder="Username" required />
          <input type="password" placeholder="New Password" required />
          <button type="submit" className="bank-button primary">Reset</button>
        </form>
      </div>
    </div>
  );
}

export default ResetPassword;
