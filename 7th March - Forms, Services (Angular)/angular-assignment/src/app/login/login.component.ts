import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../services/auth-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private authService: AuthServiceService) {}

  onSubmit(formData: any) {
    const isAuthenticated = this.authService.loginUser(formData);

    if (isAuthenticated) {
      alert("Login Successful!");
    } else {
      alert("Invalid Credentials. Please try again.");
    }
  }
}
