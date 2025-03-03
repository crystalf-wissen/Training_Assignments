abstract class Emp {
    constructor(
        public id: number,
        public name: string,
        public age: number,
        public designation: string,
        public salary: number
    ) {}
    abstract displayDetails(): void;
}

class Clerk extends Emp {
    constructor(id: number, name: string, age: number) {
        super(id, name, age, "Clerk", 20000); 
    }
    displayDetails(): void {
        console.log(`Clerk Details: ID: ${this.id}, Name: ${this.name}, Age: ${this.age}, Salary: ${this.salary}`);
    }
}

class Programmer extends Emp {
    constructor(id: number, name: string, age: number) {
        super(id, name, age, "Programmer", 30000);
    }
    displayDetails(): void {
        console.log(`Programmer Details: ID: ${this.id}, Name: ${this.name}, Age: ${this.age}, Salary: ${this.salary}`);
    }
}

class Manager extends Emp {
    constructor(id: number, name: string, age: number) {
        super(id, name, age, "Manager", 50000); 
    }
    displayDetails(): void {
        console.log(`Manager Details: ID: ${this.id}, Name: ${this.name}, Age: ${this.age}, Salary: ${this.salary}`);
    }
}

class EmpManageApp {
    private employees: Emp[] = [];
    addEmployee(emp: Emp): void {
        this.employees.push(emp);
        this.displayEmployeeInHTML(emp)
        console.log(`${emp.name} added successfully.`);
    }

    searchEmployee(id:number): void {
        let foundEmp : Emp | any
        this.employees.forEach(emp => {
            if(emp.id === id) {
                foundEmp = emp;
            }
        });
        if(foundEmp) {
            console.log("emp found:");
            foundEmp.displayDetails();
            this.displayEmployeeInHTML(foundEmp);
        } else {
            console.log(`No employee found with ID: ${id}`);
        }
    }    
    
    displayEmployees(): void {
        console.log("Employees: ");
        const employeeList = document.getElementById('employeeList');
        employeeList.innerHTML = '';

        if (this.employees.length === 0) {
            employeeList.innerHTML = '<p>No employees added yet.</p>';
        } else {
            for (const emp of this.employees) {
                emp.displayDetails();
                this.displayEmployeeInHTML(emp);
            }
        }
    }

    private displayEmployeeInHTML(emp: Emp): void {
        const employeeList = document.getElementById('employeeList');
        const li = document.createElement('li');
        li.textContent = `${emp.designation} - ID: ${emp.id}, Name: ${emp.name}, Age: ${emp.age}, Salary: ${emp.salary}`; // Set text content of li
        employeeList?.appendChild(li);
    }
    removeEmployee(id: number): void {
        let foundEmp : Emp | any
        this.employees.forEach(emp => {
            if(emp.id === id) {
                foundEmp = emp;
            }
        });
        if(foundEmp) {
            console.log(`Employee with ID: ${id} sucessfully removed`);
            this.employees.splice(this.employees.indexOf(foundEmp), 1);
        } else {
            console.log(`No employee found with ID: ${id}`);
        }
    }
}
