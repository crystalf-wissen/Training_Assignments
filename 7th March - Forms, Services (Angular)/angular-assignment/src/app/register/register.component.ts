import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { AuthServiceService } from '../services/auth-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: any;

  constructor(private authService:AuthServiceService){}

  ngOnInit() {
    this.registerForm = new FormGroup({
      customerId: new FormControl("",Validators.compose([Validators.required, Validators.pattern(/^\d{7}$/)])),
      username: new FormControl("",Validators.compose([Validators.required, Validators.minLength(5), Validators.pattern(/^[a-zA-Z]+$/)])),
      password: new FormControl("",Validators.compose([Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{8,}$/)])),
      confirmPassword: new FormControl("", this.confirmPasswordValidator.bind(this)),
      accountNo: new FormControl("", Validators.compose([Validators.required,Validators.minLength(6)])),
    },
  );
  }
  confirmPasswordValidator(control: any): any {
    const password = this.registerForm?.get('password')?.value;
    const confirmPassword = control.value;
  
    return password === confirmPassword ? null : { confirmPassword: true };
  }
  
  
  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.registerUser(this.registerForm.value);
      console.log("Registration Successful!");
      alert("User Registered Successfully!");
      this.registerForm.reset();
    } else {
      alert("Please correct the errors before submitting.");
    }
  }

}
