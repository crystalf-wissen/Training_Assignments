import { useState } from "react";

function DepositCalculator() {

    const [depositPrincipal, setDepositPrincipal] = useState('');
    const [depositTenure, setDepositTenure] = useState('');
    const [maturity, setMaturity] = useState('');

    function validateDepositPrincipal() {
        const minDepositPrincipal = 1000;
        if(depositPrincipal < minDepositPrincipal) {
            alert(`The deposit amount must be a minimum of ${minDepositPrincipal}`);
            setDepositPrincipal('');
        }
    }

    function validateDepositTenure() {
        const maxDepositTenure = 10;
        if(depositTenure > maxDepositTenure) {
            alert(`The tenure must be less than ${maxDepositTenure}`);
            setDepositTenure('');
        }
    }

    function calculateMaturityAmount() {
        const principal = parseFloat(depositPrincipal);
        const tenure = parseInt(depositTenure, 10);
        const interestRate = 7;

        if (!isNaN(principal) && !isNaN(tenure)) {
            const maturityValue = principal * Math.pow((1 + interestRate / 100), tenure);
            setMaturity(maturityValue.toFixed(2));
        } else {
            alert("Please enter valid Deposit Principal and Tenure to calculate Maturity.");
        }
    }

    function handleSubmit(e) {
        e.preventDefault();
        calculateMaturityAmount();
    }

    return(
        <div className="main-content">
            <div id="depositCalculatorContainer" className="calculator-container">
                <h1>Deposit Calculator</h1>
                <form id="depositForm" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="depositPrincipal">Deposit Principal:</label>
                        <input type="number" id="depositPrincipal" name="depositPrincipal" placeholder="Enter deposit principal" required value={depositPrincipal || ''} onChange={(e) => {setDepositPrincipal(e.target.value)}} onBlur={validateDepositPrincipal} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="depositTenure">Deposit Tenure (Years):</label>
                        <input type="number" id="depositTenure" name="depositTenure" placeholder="Enter deposit tenure" required value={depositTenure || ''} onChange={(e) => {setDepositTenure(e.target.value)}} onBlur={validateDepositTenure} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="depositInterestRate">Interest Rate (%):</label>
                        <input type="text" id="depositInterestRate" name="depositInterestRate"  value='7' readOnly />
                    </div>
                    <div className="form-group">
                        <label htmlFor="maturity">Maturity:</label>
                        <input type="text" id="maturity" name="maturity" value={maturity || ''} readOnly/>
                    </div>
                    <button type="submit">CALCULATE</button>
                </form>
            </div>
        </div>
    )
}

export default DepositCalculator;