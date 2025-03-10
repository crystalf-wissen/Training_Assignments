import { Component } from '@angular/core';
import { SquaredPipe } from '../pipe/squared.pipe';
import { PowerPipe } from '../pipe/power.pipe';
@Component({
  selector: 'app-custom-pipes',
  imports: [SquaredPipe, PowerPipe],
  templateUrl: './custom-pipes.component.html',
  styleUrl: './custom-pipes.component.css'
})
export class CustomPipesComponent {

}
