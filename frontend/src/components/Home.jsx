import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';


function Home() {
  return (
    <div className="home-container">
      <div className="bank-card">
        
        <h1 className="bank-title">Welcome to The Big 4 Bank</h1>
        <p className="bank-subtitle">Your trusted financial partner</p>
        
        <div className="button-group">
          <Link to="/create-account" className="bank-button primary">
            Create Customer Account
          </Link>
          <Link to="/employee-login" className="bank-button">
            Login as Employee
          </Link>
          <Link to="/customer-login" className="bank-button">
            Login as Customer
          </Link>
          <button className="bank-button exit">
            Exit Application
          </button>
        </div>
      </div>
    </div>
  );
}

export default Home;