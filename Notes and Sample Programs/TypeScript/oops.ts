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
export class Clerk extends Emp {
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
//No constructor overloading and overriding in TypeScript

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

var a1 = new B();

console.log("-----------------------------------------------");
// We can create object of an interface in JS
// Variables cannot be initialised in Interface
interface Car {
    speed:number;
    acclerate(): void;
    brake(): void;
}
class Hatchback implements Car {
    speed:number = 0;
    acclerate(): void {
        console.log("Acclerator : " + ++this.speed);
    }
    brake(): void {
        console.log("Decclerator : " + --this.speed);
    }
}

let h1 = new Hatchback();
h1.acclerate();
h1.acclerate();
h1.acclerate();
h1.brake();

interface CarProperties {
    company:string;
    model:string;
    price:number;
    gear?:boolean;      // optional things to add
}

let m1:CarProperties = {"company": "Maruti","model":"Brezza","price":182500, "gear":true};
let t1:CarProperties = {"company": "Tata","model":"Nexon","price":258500};
let m2:CarProperties = {"company": "M&M","model":"BE6","price":8790000};

console.log(m1);
console.log(t1);
console.log(m2);

console.log("-----------------------------------------------");
//We can use more methods and no need to worry about type cast
let arr1 = ["hi", "hello", "world", "how are you", "thanks"];
let arr2 = ["hi", "hello", "world", "how are you", "thanks"];
arr1.push("just for demo")

console.log(arr1);
console.log(arr2);