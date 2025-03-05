import { Component } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  imports: [],
  templateUrl: './event-binding.component.html',
  styleUrl: './event-binding.component.css'
})
export class EventBindingComponent {
  display(): void {
    console.log('Welcome to event binding demo');
  }

  printMessage(message: string): void {
    console.log("Recieved message: " + message);
  }
}
