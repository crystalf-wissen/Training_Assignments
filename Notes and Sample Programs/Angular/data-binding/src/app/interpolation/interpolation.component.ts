import { Component } from '@angular/core';

@Component({
  selector: 'app-interpolation',
  imports: [],
  templateUrl: './interpolation.component.html',
  styleUrl: './interpolation.component.css'
})
export class InterpolationComponent {
  name:string = 'Crystal';
  age:number = 21;

  getName():string {
    return this.name;
  }

  getAge():number {
    return this.age;
  }
}
