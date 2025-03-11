import { Injectable } from '@angular/core';
import { Employee } from './employee.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  url: string ="http://localhost:8080/employees"

  selectedEmployee: Employee = {
    eid: 0,
    name: '',
    age: 0,
    salary: 0,
    designation: ''
  };
  employees: Employee[] | null = null;

  constructor(private http: HttpClient) { }

  postEmployee(emp: Employee) {
    return this.http.post(this.url,emp);
  }

  getAllEmployees() {
    return this.http.get(this.url);
  }

  putEmployee(emp:Employee) {
    return this.http.put(this.url,emp);
  }

  deleteEmployee(id:number) {
    return this.http.delete(this.url+"/"+id);
  }
}
