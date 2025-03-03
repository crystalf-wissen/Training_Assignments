var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var Emp = /** @class */ (function () {
    function Emp(id, name, age, designation, salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
    }
    return Emp;
}());
var Clerk = /** @class */ (function (_super) {
    __extends(Clerk, _super);
    function Clerk(id, name, age) {
        return _super.call(this, id, name, age, "Clerk", 20000) || this;
    }
    Clerk.prototype.displayDetails = function () {
        console.log("Clerk Details: ID: ".concat(this.id, ", Name: ").concat(this.name, ", Age: ").concat(this.age, ", Salary: ").concat(this.salary));
    };
    return Clerk;
}(Emp));
var Programmer = /** @class */ (function (_super) {
    __extends(Programmer, _super);
    function Programmer(id, name, age) {
        return _super.call(this, id, name, age, "Programmer", 30000) || this;
    }
    Programmer.prototype.displayDetails = function () {
        console.log("Programmer Details: ID: ".concat(this.id, ", Name: ").concat(this.name, ", Age: ").concat(this.age, ", Salary: ").concat(this.salary));
    };
    return Programmer;
}(Emp));
var Manager = /** @class */ (function (_super) {
    __extends(Manager, _super);
    function Manager(id, name, age) {
        return _super.call(this, id, name, age, "Manager", 50000) || this;
    }
    Manager.prototype.displayDetails = function () {
        console.log("Manager Details: ID: ".concat(this.id, ", Name: ").concat(this.name, ", Age: ").concat(this.age, ", Salary: ").concat(this.salary));
    };
    return Manager;
}(Emp));
var EmpManageApp = /** @class */ (function () {
    function EmpManageApp() {
        this.employees = [];
    }
    EmpManageApp.prototype.addEmployee = function (emp) {
        this.employees.push(emp);
        this.displayEmployeeInHTML(emp);
        console.log("".concat(emp.name, " added successfully."));
    };
    EmpManageApp.prototype.searchEmployee = function (id) {
        var foundEmp;
        this.employees.forEach(function (emp) {
            if (emp.id === id) {
                foundEmp = emp;
            }
        });
        if (foundEmp) {
            console.log("emp found:");
            foundEmp.displayDetails();
            this.displayEmployeeInHTML(foundEmp);
        }
        else {
            console.log("No employee found with ID: ".concat(id));
        }
    };
    EmpManageApp.prototype.displayEmployees = function () {
        console.log("Employees: ");
        var employeeList = document.getElementById('employeeList');
        employeeList.innerHTML = '';
        if (this.employees.length === 0) {
            employeeList.innerHTML = '<p>No employees added yet.</p>';
        }
        else {
            for (var _i = 0, _a = this.employees; _i < _a.length; _i++) {
                var emp = _a[_i];
                emp.displayDetails();
                this.displayEmployeeInHTML(emp);
            }
        }
    };
    EmpManageApp.prototype.displayEmployeeInHTML = function (emp) {
        var employeeList = document.getElementById('employeeList');
        var li = document.createElement('li');
        li.textContent = "".concat(emp.designation, " - ID: ").concat(emp.id, ", Name: ").concat(emp.name, ", Age: ").concat(emp.age, ", Salary: ").concat(emp.salary); // Set text content of li
        employeeList === null || employeeList === void 0 ? void 0 : employeeList.appendChild(li);
    };
    EmpManageApp.prototype.removeEmployee = function (id) {
        var foundEmp;
        this.employees.forEach(function (emp) {
            if (emp.id === id) {
                foundEmp = emp;
            }
        });
        if (foundEmp) {
            console.log("Employee with ID: ".concat(id, " sucessfully removed"));
            this.employees.splice(this.employees.indexOf(foundEmp), 1);
        }
        else {
            console.log("No employee found with ID: ".concat(id));
        }
    };
    return EmpManageApp;
}());
