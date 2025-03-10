// without var or let it becomes global variable
// let if in block it will only be available there unline var which will be available within that function
// lexical scope
// global variables are only defined when initialized will value
// can only put const for local variables inside construcotr or function but not inside object/class 
// for that put readonly
function abc(){
    a = 10;
    var b = 20;
    let c = 30;
    console.log("From inside abc() "+a);
    console.log("From inside abc() "+b);
    console.log("From inside abc() "+c);
}

abc();
console.log("From outside abc() "+a);
// console.log("From outside abc() "+b);
// console.log("From outside abc() "+c);