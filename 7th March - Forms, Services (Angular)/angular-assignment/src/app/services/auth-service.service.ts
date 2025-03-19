import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private users: any[] = [];

  constructor() {}

  registerUser(user: any): void {
    this.users.push(user);
    console.log("User registered:", user);
  }

  loginUser(user: any): boolean {
    let isUserFound = false;

    for (let u of this.users) {
      if (u.username === user.username && u.password === user.password) {
        isUserFound = true;
        break;
      }
    }

    return isUserFound;
  }

}
