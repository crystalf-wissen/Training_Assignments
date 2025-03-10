import { Component } from '@angular/core';
import { FormsModule, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-reactive-forms',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './reactive-forms.component.html',
  styleUrl: './reactive-forms.component.css'
})
export class ReactiveFormsComponent {
  employeeForm: any;

  ngOnInit() {
    this.employeeForm = new FormGroup({
      user: new FormControl("", this.unameValidator),
      age: new FormControl("",Validators.compose([Validators.min(21),Validators.max(60),Validators.required,])),
      salary: new FormControl(),
      designation: new FormControl("", Validators.required)
    })
  }

unameValidator(control:any):any {
  if(control.value.length < 5) {
    return {user: true}
  }
}

  abc(obj: any){

  }
}
