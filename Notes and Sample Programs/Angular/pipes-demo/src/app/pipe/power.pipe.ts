import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'power',
  pure: false // Enable change detection
})
export class PowerPipe implements PipeTransform {

  transform(value: number, ...args: number[]): number {
    let num = args[0];
    let result = value;
    for (let i = 1; i < num; i++) {
      result *= value;
    }
    return result;
  }
}
