export default class AgendaEvent {
    constructor(date, title) {
        this._date = date;
        this._title = title;
        this._serial = Math.random().toString(36).substring(7);
    }

    get date() {
        return this._date;
    }

    set date(date) {
        this._date = date;
    }

    get title() {
        return this._title;
    }

    set title(title) {
        this._title = title;
    }

    get serial() {
        return this._serial;
    }
}
