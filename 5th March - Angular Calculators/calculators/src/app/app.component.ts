import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoanCalculatorComponent } from "./loan-calculator/loan-calculator.component";
import { DepositCalculatorComponent } from "./deposit-calculator/deposit-calculator.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoanCalculatorComponent, DepositCalculatorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'calculators';
}
