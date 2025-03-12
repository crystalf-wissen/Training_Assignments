import React, { useState } from 'react';

function LoanCalculator() {

    const [loanType, setLoanType] = useState('');
    const [interestRate, setInterestRate] = useState(null);
    const [loanAmount, setLoanAmount] = useState(null);
    const [loanTenure, setLoanTenure] = useState(null);
    const [emi, setEmi] = useState(null);

    function handleLoanTypeChange(selectedLoanType) {
        setLoanType(selectedLoanType);

        if (selectedLoanType === 'home') {
            setInterestRate(9);
        } else if (selectedLoanType === 'car') {
            setInterestRate(11);
        } else if (selectedLoanType === 'personal') {
            setInterestRate(15);
        } else {
            setInterestRate(null);
        }

        setLoanAmount(null);
        setLoanTenure(null);
        setEmi(null);
    };


    function validateLoanTenure() {
        if (loanTenure !== null) {
            const minTenure = 1;
            let maxTenure = 0;

            if (loanType === 'home') {
                maxTenure = 30;
            } else if (loanType === 'car') {
                maxTenure = 7;
            } else if (loanType === 'personal') {
                maxTenure = 5;
            }

            if (loanTenure > maxTenure || loanTenure < minTenure) {
                alert(`For ${loanType} loan, the tenure should be between ${minTenure} and ${maxTenure} years.`);
                setLoanTenure(null);
            }
        }
    };

    function validateLoanAmount() {
        if (loanAmount !== null) {
            let minAmount = 0;
            let maxAmount = 0;

            if (loanType === 'home') {
                minAmount = 500000;
                maxAmount = 10000000;
            } else if (loanType === 'car') {
                minAmount = 100000;
                maxAmount = 1500000;
            } else if (loanType === 'personal') {
                minAmount = 10000;
                maxAmount = 500000;
            }

            if (loanAmount < minAmount || loanAmount > maxAmount) {
                alert(`For ${loanType} loan, the amount should be between ${minAmount} and ${maxAmount}.`);
                setLoanAmount(null);
            }
        }
    };

    function calculateEMI() {
        if (loanAmount && interestRate && loanTenure) {
            const monthlyRate = interestRate / 100 / 12;
            const months = loanTenure * 12;
            const emiValue = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
            setEmi(emiValue.toFixed(2));
        }
    };

    function handleSubmit(e) {
        e.preventDefault();
        calculateEMI();
    };

    return (
        <div className="main-content">
        <div id="loanCalculatorContainer" className="calculator-container">
            <h1>Loan Calculator</h1>
            <form id="loanForm" onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="loanType">Loan Type:</label>
                <select id="loanType" name="loanType" value={loanType} onChange={(e)=>{handleLoanTypeChange(e.target.value)}} required >
                <option value="">Select Loan Type</option>
                <option value="home">Home Loan</option>
                <option value="car">Car Loan</option>
                <option value="personal">Personal Loan</option>
                </select>
            </div>

            <div className="form-group">
                <label htmlFor="interestRate">Interest Rate:</label>
                <input type="text" id="interestRate" name="interestRate" value={interestRate || ''} readOnly />
            </div>

            <div className="form-group">
                <label htmlFor="loanTenure">Loan Tenure (Years):</label>
                <input type="number" id="loanTenure" value={loanTenure || ''} onChange={(e) => {setLoanTenure(e.target.value)}} onBlur={validateLoanTenure} required />
            </div>

            <div className="form-group">
                <label htmlFor="loanAmount">Loan Amount:</label>
                <input type="number" id="loanAmount" value={loanAmount || ''} onChange={(e) => {setLoanAmount(e.target.value)}} onBlur={validateLoanAmount} required />
            </div>

            <div className="form-group">
                <label htmlFor="emi">EMI (Monthly Installment):</label>
                <input type="text" id="emi" name="emi" value={emi || ''} readOnly />
            </div>

            <button type="submit">CALCULATE</button>
            </form>
        </div>
        </div>
    );
}

export default LoanCalculator;