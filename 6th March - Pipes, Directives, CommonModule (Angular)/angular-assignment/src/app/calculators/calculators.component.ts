import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { LoanComponent } from "./loan/loan.component";
import { DepositComponent } from "./deposit/deposit.component";
import { MutualFundsComponent } from "./mutual-funds/mutual-funds.component";

@Component({
  selector: 'app-calculators',
  imports: [CommonModule, LoanComponent, DepositComponent, MutualFundsComponent],
  templateUrl: './calculators.component.html',
  styleUrl: './calculators.component.css'
})
export class CalculatorsComponent {
  selectedCalculator: string = '';
  onSelectCalculator(value: string):void { 
    this.selectedCalculator = value;
  }

}
