import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'squared'
})
export class SquaredPipe implements PipeTransform {

  transform(value: number): number {
    return value * value;
  }

}
