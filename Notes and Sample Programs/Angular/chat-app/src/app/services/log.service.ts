import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor() {
    console.log('LogService constructor called');
  }

  logMessage(msg:string) :void {
    console.log(msg);
  }
}
