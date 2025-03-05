import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ParentComponent } from "./parent/parent.component";
import { MyCompComponent } from './my-comp/my-comp.component';

@Component({
  selector: 'app',
  imports: [RouterOutlet, ParentComponent, MyCompComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  greet:string = "GOOD MORNING"

  setGreet() : string {
    return "GOOD AFTERNOON"
  }
}
