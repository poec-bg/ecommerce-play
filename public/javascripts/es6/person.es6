export default class Person {
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