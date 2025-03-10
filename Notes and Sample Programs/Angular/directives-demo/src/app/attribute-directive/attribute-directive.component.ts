import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-attribute-directive',
  imports: [CommonModule],
  templateUrl: './attribute-directive.component.html',
  styleUrl: './attribute-directive.component.css'
})
export class AttributeDirectiveComponent {
  brderColor: boolean = false;
  bgColor: boolean = false;
  color: string = 'blue';

  toggleBorderColor(): void {
    this.brderColor =!this.brderColor;
  }
  toggleBgColor(): void {
    this.bgColor =!this.bgColor;
  }

  setColor(color: string): void {
    this.color = color;
    } 
}
