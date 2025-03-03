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
    function Emp(name, age) {
        this.name = name;
        this.age = age;
    }
    Emp.prototype.display = function () {
        console.log("Name: ".concat(this.name, ", Age: ").concat(this.age));
    };
    return Emp;
}());
//in js you can pass howmany arguments u want
var Clerk = /** @class */ (function (_super) {
    __extends(Clerk, _super);
    function Clerk(name, age, salary, designation) {
        var _this = _super.call(this, name, age) || this;
        _this.salary = salary;
        _this.designation = designation;
        return _this;
    }
    Clerk.prototype.displayAll = function () {
        this.display();
        console.log("Salary: ".concat(this.salary, ", Designation: ").concat(this.designation));
    };
    return Clerk;
}(Emp));
var e1 = new Clerk("Raju", 25, 30000, "Tester");
e1.displayAll();
var e2 = new Clerk("Crystal", 22, 500000, "Manager");
e2.displayAll();
//If the parent class has a constructor and you don't call super() in the child class, you will get a syntax error because TypeScript (and JavaScript) requires that you call the parent class's constructor before using this in the child class.
//No constructor overloading in TypeScript
console.log("----------------------------------------------------------------");
var A = /** @class */ (function () {
    function A() {
        console.log("A constructor");
    }
    return A;
}());
var B = /** @class */ (function (_super) {
    __extends(B, _super);
    function B() {
        var _this = this;
        console.log("B constructor");
        return _this;
    }
    return B;
}(A));
var a1 = new B(); // Outputs: A constructor, B constructor
