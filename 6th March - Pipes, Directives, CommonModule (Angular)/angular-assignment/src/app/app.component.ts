import { Component } from '@angular/core';
import { CircleComponent } from './circle/circle.component';
import { CalculatorsComponent } from "./calculators/calculators.component";
import { CurrencyConverterComponent } from "./currency-converter/currency-converter.component";

@Component({
  selector: 'app-root',
  imports: [CircleComponent, CalculatorsComponent, CurrencyConverterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-assignment';
}
