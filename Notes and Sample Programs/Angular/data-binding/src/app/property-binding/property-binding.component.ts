import { Component } from '@angular/core';

@Component({
  selector: 'app-property-binding',
  imports: [],
  templateUrl: './property-binding.component.html',
  styleUrl: './property-binding.component.css'
})
export class PropertyBindingComponent {
  name:string = 'Carina';
  married:boolean = true;
  greet(): string{
    return "Good afternoon!";
  }
  uiType: string = 'number';
}
