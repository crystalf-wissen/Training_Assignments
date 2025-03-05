import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposit-calculator',
  imports: [FormsModule],
  templateUrl: './deposit-calculator.component.html',
  styleUrl: './deposit-calculator.component.css'
})
export class DepositCalculatorComponent {
  depositPrincipal: number | null = null;
  depositTenure: number | null = null;
  maturity: number | null = null;
  depositInterestRate: number = 7;

  validateDepositPrincipal(): void {
    var minPrincipal = 1000;
    if(this.depositPrincipal !==null) {
      if (!isNaN(this.depositPrincipal)) {
        if (this.depositPrincipal < minPrincipal ) {
            alert(`Principal should be atleast ${minPrincipal}.`);
            this.depositPrincipal = null;
        }
      }
    }
  }

  validateDepositTenure(): void {
    var minTenure = 1;
    var maxTenure = 10
    if(this.depositTenure !==null) {
      if (!isNaN(this.depositTenure)) {
        if (this.depositTenure < minTenure || this.depositTenure >maxTenure ) {
            alert(`Tenure should be atleast ${minTenure} years and maximum of ${maxTenure}`);
            this.depositTenure = null;
        }
      }
    }
  }

  calculateMaturity(): void {
    if(this.depositInterestRate && this.depositPrincipal && this.depositTenure) {
      this.maturity = this.depositPrincipal * Math.pow(1 + this.depositInterestRate / 100, this.depositTenure);
      console.log(this.maturity);
    }
}
}
