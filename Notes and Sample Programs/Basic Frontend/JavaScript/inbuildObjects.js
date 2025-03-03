console.log(Math.PI);
console.log(Math.E);

console.log(Math.sqrt(25));
console.log(Math.pow(3, 6));

console.log("----------------------------");

var d1 = new Date();
console.log(d1);

d1.setMonth(15); // Note: Month is 0-indexed, so 15 means 16th month, which will wrap to the next year
console.log(d1);

console.log("----------------------------");

var s1 = new String("Hello Everybody");
console.log(s1);

console.log(s1.toUpperCase());
console.log(s1.toLowerCase());

console.log("----------------------------");

var n1 = new Number("3234");
console.log(n1);

console.log("----------------------------");

console.log(new Boolean("false"));
console.log(new Boolean(""));
console.log(new Boolean(0));
console.log(new Boolean(null));
console.log(new Boolean(NaN));
console.log(new Boolean(false));
console.log(new Boolean());