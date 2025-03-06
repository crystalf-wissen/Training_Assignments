import { Component } from '@angular/core';
import { MoneyPipe } from '../pipe/money.pipe';
@Component({
  selector: 'app-currency-converter',
  imports: [MoneyPipe],
  templateUrl: './currency-converter.component.html',
  styleUrl: './currency-converter.component.css'
})
export class CurrencyConverterComponent {
  amt:number = 200000;
}
