function add(a=0,b=0){
    console.log("Result: " + (a+b));
}

add(10,20);
add(10);
add();
add(30,40);

//arrow function doesnt create a separate this function object otherwise function in a function in a function, separte objects will be created internally acc to no of functions

console.log("----------------------------------------------------------------")
class Person {
    name:string;
    constructor(name){
        console.log("Start of the constructor");
        this.name = name;
        setTimeout(()=>{
            console.log("From within constructor: : " + this.name);
        }, 1000)
        console.log("End of the constructor");
    }
}
new Person("Crystal")
console.log("This statement is not waiting for anyone")

console.log("----------------------------------------------------------------");
//Closures
var increment = function increment(){
    var a = 0;
    var plus = function () {
        a++;
        console.log(a);
    }
    return plus;
} ();

increment();
increment();
increment();

console.log("----------------------------------------------------------------");
//Event bubbling
