import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-loan-calculator',
  imports: [FormsModule],
  templateUrl: './loan-calculator.component.html',
  styleUrl: './loan-calculator.component.css'
})
export class LoanCalculatorComponent {
  loanType: string = '';
  interestRate: number | null = null;
  loanAmount: number | null = null;
  loanTenure: number | null = null;
  emi: number | null = null;
  updateDetails(): void {
    if(this.loanType=='home'){
        this.interestRate = 9;
    } else if (this.loanType=='car'){
        this.interestRate = 11;
    } else if (this.loanType=='personal'){
        this.interestRate = 15;
    }
    this.loanAmount = null;
    this.loanTenure = null;
    this.emi = null;
  }

  validateLoanTenure(): void {
    if (this.loanTenure !== null) {
      const minTenure = 0;
      if(this.loanType=='home') {
          const maxTenure = 30;
          if (!isNaN(this.loanTenure)) {
              if (this.loanTenure > maxTenure || this.loanTenure <= minTenure) {
                  alert(`For Home Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                  this.loanTenure = null;
              }
          }
      } else if (this.loanType=='car'){
          const maxTenure = 7;
          if (!isNaN(this.loanTenure)) {
              if (this.loanTenure > maxTenure || this.loanTenure <= minTenure) {
                  alert(`For Car Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                  this.loanTenure = null;
              }
          }
      } else if (this.loanType=='personal'){
          const maxTenure = 5;
          if (!isNaN(this.loanTenure)) {
              if (this.loanTenure > maxTenure || this.loanTenure <= minTenure) {
                  alert(`For Personal Loan, tenure should be less than ${maxTenure} years and greater than ${minTenure}.`);
                  this.loanTenure = null;
              }
          }
      }
    }
  }

  validateLoanAmount(): void {
    if(this.loanAmount!==null){
      if(this.loanType=='home') {
        const minAmount = 500000;
        const maxAmount = 10000000;
        if (!isNaN(this.loanAmount)) { 
            if (this.loanAmount < minAmount || this.loanAmount > maxAmount) {
                alert(`For Home Loan, amount should be between ${(minAmount)} and ${(maxAmount)}.`);
                this.loanAmount = null;
            }
        }
    } else if (this.loanType=='car'){
        const minAmount = 100000;
        const maxAmount = 1500000;
        if (!isNaN(this.loanAmount)) {
            if (this.loanAmount < minAmount || this.loanAmount > maxAmount) {
                alert(`For Car Loan, amount should be between ${(minAmount)} and ${(maxAmount)}.`);
                this.loanAmount = null;
            }
        }
    } else if (this.loanType=='personal'){
        const minAmount = 10000;
        const maxAmount = 500000;
        if (!isNaN(this.loanAmount)) {
            if (this.loanAmount < minAmount || this.loanAmount > maxAmount) {
                alert(`For Personal Loan, amount should be between ${(minAmount)} and ${(maxAmount)}.`);
                this.loanAmount = null;
            }
        }
      }
    }   
  }

  calculateEMI(): void {
    if (this.loanAmount && this.interestRate && this.loanTenure) {
      const monthlyRate = this.interestRate / 100 / 12;  
      const months = this.loanTenure * 12;
      this.emi = this.loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
      console.log(this.emi);
    }
  }
  

}
