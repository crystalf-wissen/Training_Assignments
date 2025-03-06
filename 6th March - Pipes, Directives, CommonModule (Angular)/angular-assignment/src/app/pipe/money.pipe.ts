import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'money'
})
export class MoneyPipe implements PipeTransform {

  private conversions: { [key: string]: number } = {
    'USD': 0.011,
    'JPY': 1.70,
    'SAR': 0.043,
    'EUR': 0.011,
    'AUD': 0.018,
  }

  transform(value: number, ...args: string[]): string {
    const targetCurrency = args[0];
    const conversionRate = this.conversions[targetCurrency];
    const convertedValue = value * conversionRate;
    if(conversionRate) {
      return `${convertedValue.toFixed(2)} ${targetCurrency}`;
    } else {
      return `${value.toFixed(2)} INR`;
    }
  }
}
