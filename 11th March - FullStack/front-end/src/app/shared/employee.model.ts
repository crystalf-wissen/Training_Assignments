export class Employee {
    eid: number | 0;
    name: string;
    age: number | 0;
    salary: number | 0;
    designation: string;

    constructor(eid: number, name: string, age: number, salary: number, designation: string) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }
}
