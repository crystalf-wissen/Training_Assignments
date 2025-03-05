import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InterpolationComponent } from "./interpolation/interpolation.component";
import { PropertyBindingComponent } from "./property-binding/property-binding.component";
import { EventBindingComponent } from "./event-binding/event-binding.component";
import { TwoWayBindingComponent } from './two-way-binding/two-way-binding.component';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, InterpolationComponent, PropertyBindingComponent, EventBindingComponent, TwoWayBindingComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'data-binding';
}
