import { Component } from '@angular/core';
import { EmployeeComponent } from "./employee/employee.component";
import { LifeCycleComponent } from "./life-cycle/life-cycle.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule, EmployeeComponent, LifeCycleComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-second-app';
  flag:boolean = true;
  toggle():void {
    this.flag =!this.flag;
    console.log(this.flag);
  }
}
