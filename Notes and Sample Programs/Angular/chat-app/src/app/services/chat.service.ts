import { Injectable } from '@angular/core';
import { LogService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  public messages: string[] = [];

  constructor(private ls:LogService) { 
    console.log('Chat Service constructor');
  }

  chatMessage(msg: string): void {
    this.messages.push(msg);
    this.ls.logMessage(msg);
  }
}
