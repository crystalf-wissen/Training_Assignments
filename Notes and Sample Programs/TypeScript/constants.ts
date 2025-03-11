class Emp {
    name: string;
    age: number;
    readonly company: string = "Wissen";
  
    constructor(name: string, age: number) {
      this.name = name;
      this.age = age;
    }
  
    display() {
      console.log("Name: " + this.name);
      console.log("Age: " + this.age);
      console.log("Company: " + this.company);
    }
  }
  
  let e1 = new Emp("Raju", 25);
  e1.display();
  
  // e1.company = "Morgan"; // Uncommenting this will cause an error
  e1.display();