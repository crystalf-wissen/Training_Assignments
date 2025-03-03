var a = 10
var b = "10"

//difference between == and ===
console.log("a==b: "+(a==b));
console.log("a===b: "+(a===b));

//this is how to define an array
var arr = [11,22,33,44,55]
console.log("arr[0]")

//we can define a list in the same way
var arr = [11,"hi",new Date(),3.4,false]        
for(var i=0; i<arr.length; i++){
    console.log(arr[i]);
}
//difference between for-in and for-of
console.log("--------------------------------")
for(let x in arr){
    console.log("Key: "+x+", Value: "+arr[x]);
}
console.log("--------------------------------")
for(let x of arr){
    console.log(x)
}