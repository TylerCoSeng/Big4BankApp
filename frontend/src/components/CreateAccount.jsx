import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/CreateAccount.css';

function CreateAccount() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    //account creation
    if (username && password) {
      console.log(`Account created for ${username}`);
      
      

      // Redirect to home
      navigate('/');
    }
  };

  return (
    <div className="page-container">
      <div className="form-card">
        <h2>Create Customer Account</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit" className="bank-button primary">
            Create Account
          </button>
        </form>
      </div>
    </div>
  );
}

export default CreateAccount;
