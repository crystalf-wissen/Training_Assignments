// loan calculator
function validateLoanAmount(){
    var loanType = document.getElementById('loanType').value; 
    var loanAmountInput = document.getElementById('loanAmount');
    var loanAmount = parseFloat(loanAmountInput.value);
    if(loanType=='home') {
        const minAmount = 500000;
        const maxAmount = 10000000;
        if (!isNaN(loanAmount)) { 
            if (loanAmount < minAmount || loanAmount > maxAmount) {
                alert(`For Home Loan, amount should be between ${formatCurrency(minAmount)} and ${formatCurrency(maxAmount)}.`);
                loanAmountInput.value = '';
            }
        }
    } else if (loanType=='car'){
        const minAmount = 100000;
        const maxAmount = 1500000;
        if (!isNaN(loanAmount)) {
            if (loanAmount < minAmount || loanAmount > maxAmount) {
                alert(`For Car Loan, amount should be between ${formatCurrency(minAmount)} and ${formatCurrency(maxAmount)}.`);
                loanAmountInput.value = '';
            }
        }
    } else if (loanType=='personal'){
        const minAmount = 10000;
        const maxAmount = 500000;
        if (!isNaN(loanAmount)) {
            if (loanAmount < minAmount || loanAmount > maxAmount) {
                alert(`For Personal Loan, amount should be between ${formatCurrency(minAmount)} and ${formatCurrency(maxAmount)}.`);
                loanAmountInput.value = '';
            }
        }
    }
}
function validateLoanTenure() {
    var loanType = document.getElementById('loanType').value; 
    var loanTenureInput = document.getElementById('loanTenure');
    var loanTenure = parseInt(loanTenureInput.value);
    var minTenure = 0;
    if(loanType=='home') {
        const maxTenure = 30;
        if (!isNaN(loanTenure)) {
            if (loanTenure > maxTenure || loanTenure <= minTenure) {
                alert(`For Home Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                loanTenureInput.value = '';
            }
        }
    } else if (loanType=='car'){
        const maxTenure = 7;
        if (!isNaN(loanTenure)) {
            if (loanTenure > maxTenure || loanTenure <= minTenure) {
                alert(`For Car Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                loanTenureInput.value = '';
            }
        }
    } else if (loanType=='personal'){
        const maxTenure = 5;
        if (!isNaN(loanTenure)) {
            if (loanTenure > maxTenure || loanTenure <= minTenure) {
                alert(`For Personal Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                loanTenureInput.value = '';
            }
        }
    }
}

function updateDetails() {
    var loanType = document.getElementById('loanType').value;
    var interestRate = document.getElementById('interestRate');
    if(loanType=='home'){
        interestRate.value = 9;
    } else if (loanType=='car'){
        interestRate.value = 11;
    } else if (loanType=='personal'){
        interestRate.value = 15;
    }
    document.getElementById('loanAmount').value = '';
    document.getElementById('loanTenure').value = '';
    document.getElementById('emi').value = '';
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR', minimumFractionDigits: 2 }).format(amount);
}

// emi = [p * r * (1+r)^n] / [((1 + r)^n) - 1]
function calculateEMI() {
    var loanAmount = parseFloat(document.getElementById('loanAmount').value);
    var loanTenure = parseInt(document.getElementById('loanTenure').value);
    var interestRate = parseFloat(document.getElementById('interestRate').value) / 100;
    var emi = loanAmount * (interestRate * Math.pow(1 + interestRate, loanTenure)) / (Math.pow(1 + interestRate, loanTenure) - 1);
    var emiField = document.getElementById('emi');
    emiField.value = emi
}

//deposit calculator
function validateDepositPrincipal(){
    var depositPrincipal = document.getElementById('depositPrincipal').value;
    var minPrincipal = 1000;
    if (!isNaN(depositPrincipal)) {
        if (depositPrincipal < minPrincipal ) {
            alert(`Principal should be atleast ${minPrincipal}.`);
            document.getElementById('depositPrincipal').value = '';
        }
    }
}

// M = P * (1+r)^t
function calculateMaturity() {
    var depositPrincipal = parseFloat(document.getElementById('depositPrincipal').value);
    var interestRate = parseFloat(document.getElementById('depositInterestRate').value) / 100;
    var years = parseInt(document.getElementById('depositTenure').value);
    var maturity = depositPrincipal * Math.pow(1 + interestRate, years);
    var maturityField = document.getElementById('maturity');
    maturityField.value = maturity
}