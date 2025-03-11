import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from '../shared/employee.service';
import { Employee } from '../shared/employee.model';

@Component({
  selector: 'app-employee',
  standalone: false,
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css',
  providers: [EmployeeService]
})
export class EmployeeComponent implements OnInit {

  update: boolean = false;

  constructor(public es: EmployeeService) {
  }

  ngOnInit(): void {
    this.resetForm();
  }

  onSubmit(form: NgForm) {
    if(!this.update) {
      console.log(form.value);
      this.es.postEmployee(form.value).subscribe((res)=>{
        this.resetForm(form);
        console.log('Employee added successfully!');
      })
    } else {
      this.es.putEmployee(form.value).subscribe((res)=>{
        this.resetForm(form);
        this.update = false;
        console.log('Employee updated successfully!');
      })
    }
  }

  resetForm(form?: NgForm) {
    if (form) {
      form.reset();
    }
    this.es.selectedEmployee ={ 
      eid: 0,
      name: '',
      age: 0,
      salary: 0,
      designation: ''
    }
    this.refreshEmployees();
  }

  refreshEmployees() {
    this.es.getAllEmployees().subscribe((res)=>{
      this.es.employees = res as Employee[];
    })
  }

  updateEmp(emp: Employee) {
    this.update = true;
    this.es.selectedEmployee = emp;
  }

  deleteEmp(id: number) {
    if(confirm("Do you realla want to delete")) {
      this.es.deleteEmployee(id).subscribe((resp)=>{
        this.resetForm();
      })
    }
  }

}
