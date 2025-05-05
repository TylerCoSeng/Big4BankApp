import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/CreateAccount.css';

function CreateAccount() {
  const [form, setForm] = useState({
    username: '',
    password: ''
  });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Match the endpoint and data structure from your Main.java
      await axios.post('http://localhost:8080/api/accounts', {
        custUsername: form.username,
        custPassword: form.password
      });
      alert('Account created successfully!');
      navigate('/');
    } catch (error) {
      alert(`Error: ${error.response?.data?.message || error.message}`);
    }
  };

  return (
    <div className="create-container">
      <h2>Create Customer Account</h2>
      <form onSubmit={handleSubmit} className="create-form">
        <label>
          Username:
          <input 
            type="text" 
            name="username" 
            value={form.username} 
            onChange={(e) => setForm({...form, username: e.target.value})} 
            required
          />
        </label>

        <label>
          Password:
          <input 
            type="password" 
            name="password" 
            value={form.password} 
            onChange={(e) => setForm({...form, password: e.target.value})} 
            required
          />
        </label>

        <button type="submit">Create Account</button>
      </form>
    </div>
  );
}

export default CreateAccount;