import Person from './person.es6'
export default class Pilot extends Person{
    constructor(name, vehicule) {
        super(name);
        this._vehicule = vehicule;
    }
    
    get vehicule(){
        return this._vehicule;
    }
}