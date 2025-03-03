class Emp {
    name:string;
    age:number;
    constructor(name:string, age:number) {
        this.name = name;
        this.age = age;
    }  
    display() {
        console.log(`Name: ${this.name}, Age: ${this.age}`);
    }
}
//in js you can pass howmany arguments u want
class Clerk extends Emp {
    salary:number;
    designation:string;
    constructor(name:string, age:number, salary:number, designation:string) {
        super(name, age);
        this.salary = salary;
        this.designation = designation;
    }   
    displayAll() {
        this.display();
        console.log(`Salary: ${this.salary}, Designation: ${this.designation}`);
    }
}
var e1 = new Clerk("Raju", 25, 30000, "Tester");
e1.displayAll();

var e2 = new Clerk("Crystal", 22, 500000, "Manager");
e2.displayAll();

//If the parent class has a constructor and you don't call super() in the child class, you will get a syntax error because TypeScript (and JavaScript) requires that you call the parent class's constructor before using this in the child class.
//No constructor overloading in TypeScript

console.log("----------------------------------------------------------------");
class A {
    constructor() {
        console.log("A constructor");
     }
}

class B extends A {
    constructor() {
        super();
        console.log("B constructor");
    }
}

var a1= new B();