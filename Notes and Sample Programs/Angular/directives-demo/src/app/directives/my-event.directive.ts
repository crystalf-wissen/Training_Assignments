import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[MyEvent]'
})
export class MyEventDirective {

  constructor() { }

  @HostListener('click')
  abc(){
    console.log("Directive MyEventDirective executed");
  }

  @HostListener('click')
  xyz(){
    console.log("You have clicked on this event");
  }

  @HostListener('mouseenter')
  efg(){
    console.log("Mouse entered on this element");
  }

  @HostListener('mouseleave')
  hij(){
    console.log("Mouse entered on this element");
  }

}

