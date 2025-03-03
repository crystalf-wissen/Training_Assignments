function display(obj)
{
  console.log("Name : "+obj.name);
  console.log("Age : "+obj.age);
  console.log("Salary : "+obj.salary);
  console.log("Designation : "+obj.designation);
  console.log("----------------------");
}

console.log("\n<---- Using Object as a constructor ---->\n");

var emp1 = new Object();
emp1.name = "Raju";
emp1.age = 25;
emp1.salary = 30000;
emp1.designation = "Tester";

display(emp1);

console.log("\n<---- Using function as a constructor ---->\n");

function Emp(n, a, s, d)
{
  this.name = n;
  this.age = a;
  this.salary = s;
  this.designation = d;
}

var emp2 = new Emp("Sunita", 33, 60000, "Programmer");
var emp3 = new Emp("Anita", 43, 120000, "Manager");

display(emp2);
display(emp3);

console.log("\n<---- Using json format ---->\n");

var emp4 = { "name": "Ravindra", "age": 44, "salary": 400000, "designation": "Manager" };

display(emp4);

console.log("\n<---- Using ES6 classes ---->\n");

class Employee {
    constructor(n, a, s, d) {
      this.name = n;
      this.age = a;
      this.salary = s;
      this.designation = d;
    }
  
    display() {
      console.log("Name : " + this.name);
      console.log("Age : " + this.age);
      console.log("Salary : " + this.salary);
      console.log("Designation : " + this.designation);
      console.log();
    }
  }
  
var emp5 = new Employee("Hameed", 32, 50000, "Programmer");
var emp6 = new Employee("John", 42, 250000, "Manager");

emp5.display();
emp6.display();

console.log("===========================");

console.log(typeof(emp1));
console.log(typeof(emp2));
console.log(typeof(emp3));
console.log(typeof(emp4));
console.log(typeof(emp5));
console.log(typeof(emp6));