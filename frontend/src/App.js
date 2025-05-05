import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './components/Home';
import CreateAccount from './components/CreateAccount';
import CustomerLogin from './components/CustomerLogin';
import CustomerDashboard from './components/CustomerDashboard';
import AccountDetails from './components/AccountDetails';
import Deposit from './components/Deposit';
import Withdraw from './components/Withdraw';
import TransactionHistory from './components/TransactionHistory';
import EmployeeLogin from './components/EmployeeLogin';
import Dashboard from './components/Dashboard'; 
import TransferFunds from './components/TransferFunds'; 
import DeleteAccount from './components/DeleteAccount';
import ResetPassword from './components/ResetPassword'; 
import Exit from './components/Exit'; 

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/create-account" element={<CreateAccount />} />
        <Route path="/customer-login" element={<CustomerLogin />} />
        <Route path="/customer-dashboard" element={<CustomerDashboard />} />
        <Route path="/account-details" element={<AccountDetails />} />
        <Route path="/deposit" element={<Deposit />} />
        <Route path="/withdraw" element={<Withdraw />} />
        <Route path="/transaction-history" element={<TransactionHistory />} />
        <Route path="/employee-login" element={<EmployeeLogin />} />
        <Route path="/employee-dashboard" element={<Dashboard />} />
        <Route path="/transfer-funds" element={<TransferFunds />} />
        <Route path="/delete-account" element={<DeleteAccount />} />
        <Route path="/reset-password" element={<ResetPassword />} /> {}
        <Route path="/exit" element={<Exit />} /> {}
      </Routes>
    </Router>
  );
};

export default App;
