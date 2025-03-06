import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-circle',
  imports: [],
  templateUrl: './circle.component.html',
  styleUrl: './circle.component.css'
})
export class CircleComponent {
  @Input({required:true})
  radius: number = 10;
}
