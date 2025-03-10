import { Component, DoCheck, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-life-cycle',
  imports: [],
  templateUrl: './life-cycle.component.html',
  styleUrl: './life-cycle.component.css'
})
export class LifeCycleComponent implements OnInit, OnChanges, DoCheck, OnDestroy {
  constructor() {
    console.log('Constructor() is called...');
  }
  ngDoCheck(): void {
    console.log('ngDoCheck() is called...');
  }
  ngOnDestroy(): void {
    console.log('ngOnDestroy() is called...');
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log('ngOnChanges() is called...');
  }
  ngOnInit() {
    console.log('ngOnInit() is called...');
  }
}
