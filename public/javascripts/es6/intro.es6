/* ==========================================================================
 LET variable
 ========================================================================== */

let x = "test";

//Problème de scope

/*

var foo = 1;
if(true){
    var foo = 2;
}

console.log("foo => " + foo);

let bar = 1;
if(true){
    let bar = 2;
}

console.log("bar => " + bar);

*/








/* ==========================================================================
 CONST declaration
 ========================================================================== */

const PI = 3.14;

// Le code ci-dessous ne va pas compiler

// PI = 4;







/* ==========================================================================
 MULTIPLE RETURNS
 ========================================================================== */


let word, sum;
let person = {},
    personName = "John Doe";

function getData(){
    let myString = "test";
    let area = PI * 4 * 4;
    let isOk = true;
    let name = {name:personName};
    return [myString, area, isOk, name];
}

[word, sum, , person] = getData();

console.log("word => " + word);
console.log("sum => " + sum);
console.log("person => " + person.name);









/* ==========================================================================
 OBJECT DESTRUCTURING
 ========================================================================== */

/*

 let person = {firstName: "John", lastName: "Doe", age: 25, genre: "male"};
 let {firstName, age} = person;

 console.log("FirstName : " + firstName);
 console.log("Age : " + age);

 */









/* ==========================================================================
 FAT ARROW
 ========================================================================== */

/*

var getArea = function(radius){
    return PI * radius * radius;
}

console.log("javascript 1.5 => " + getArea(5));



let es6_getArea = (radius) => PI * radius * radius;

console.log("javascript 1.6 => " + es6_getArea(5));

*/







/* ==========================================================================
 TEMPLATE STRING
 ========================================================================== */

/*

var age = 25,
    firstName = "John",
    lastName = "Doe";

var presentation = "My name is " + firstName + " " + lastName + ".\n" +
                    "I'm " + age + " years old";

console.log("javascript 1.5 => \n" + presentation);


let es6_presentation = `My name is ${firstName} ${lastName}.
I'm ${age} years old.`;

console.log("javascript 1.6 => \n" + es6_presentation);

*/









/* ==========================================================================
 GENERATORS
 ========================================================================== */

/*

function* indexGenerator(){
    var index = 0;
    while(true)
        yield index++;
}

var getIndex = indexGenerator();

console.log("New index = " + getIndex.next().value);
console.log("New index = " + getIndex.next().value);
console.log("New index = " + getIndex.next().value);
console.log("New index = " + getIndex.next().value);

*/










/* ==========================================================================
 MAP
 ========================================================================== */

/*

let person1 = {name: "John Doe"},
    person2 = {name: "Jane Doe"};

let persons = new Map();
persons.set("male", person1);
persons.set("female", person2);

console.log("Persons size : " + persons.size);
console.log("Female : " + JSON.stringify(persons.get("female")));

let person3 = {name: "Arthur Mitchel"};
persons.set("male", person3);

console.log("Male : " + JSON.stringify(persons.get("male")));

*/











/* ==========================================================================
 SET
 ========================================================================== */

/*

const numbers = [5, 1, 5, 7, 7, 5];
const finalList = new Set(numbers);

console.log(finalList);

*/












/* ==========================================================================
 CLASSES
 ========================================================================== */

/*

class Person {
    constructor(name) {
        this._name = name;
        this._age = 0;
    }

    get age() {
        return this._age;
    }

    set age(value) {
        if (value < 0 || value > 130) {
            console.log("You can't do that");
        }
        this._age = value;
    }


    sayHello() {
        console.log(`Hi, the name is ${this._name} and I'm ${this._age} years old`);
    }
}

let franck = new Person("Franck");
franck.age = 32;
franck.sayHello();

*/

