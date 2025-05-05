import React, { useState } from 'react';
import '../styles/TransferFunds.css';

function TransferFunds() {
  const [transferData, setTransferData] = useState({
    fromAccount: '',
    toAccount: '',
    amount: ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    alert('Transfer submitted (feature not yet connected to backend)');
  };

  return (
    <div className="transfer-container">
      <h2>Transfer Funds</h2>
      <form onSubmit={handleSubmit} className="transfer-form">
        <label>
          From Account:
          <input
            type="text"
            value={transferData.fromAccount}
            onChange={(e) =>
              setTransferData({ ...transferData, fromAccount: e.target.value })
            }
            required
          />
        </label>
        <label>
          To Account:
          <input
            type="text"
            value={transferData.toAccount}
            onChange={(e) =>
              setTransferData({ ...transferData, toAccount: e.target.value })
            }
            required
          />
        </label>
        <label>
          Amount:
          <input
            type="number"
            value={transferData.amount}
            onChange={(e) =>
              setTransferData({ ...transferData, amount: e.target.value })
            }
            required
          />
        </label>
        <button type="submit">Transfer</button>
      </form>
    </div>
  );
}

export default TransferFunds;
