let moment = require('moment');
import AgendaEvent from './agendaEvent.es6'

let months = moment.months(),
    now = moment(),
    numOfDaysThisMonth = moment(now).daysInMonth(),
    daysOfWeek = ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
    daysElements = "",
    weekIndex = 0,
    isSet = false,
    agendaEvents = [];

$(function(){

    // Ajout de la liste des mois dans la vue
    let monthsElements = "";
    for(let month of months){
    monthsElements += `<span class="month">${month}</span>`;
    }
    $('.months').html(monthsElements);

    // Ajout des titres des colonnes pour les jours de la semaine
    for(let dayOfWeek of daysOfWeek){
        $('#agenda-days').append(`<div class="day-title cell">${dayOfWeek}</div>`);
    }

    // Ajout de la liste des jours dans la vue
    for(let index = 1; index <= numOfDaysThisMonth; index++){
       let currentDay = moment().date(index);
       addDayCell(currentDay);
    }
    $('#days-container').html(daysElements);



    $(document).on('click', '.add-event-link', function(evt){
        evt.preventDefault();
        agendaEvents.push(new AgendaEvent($(this).parents('.day').data('day'), "New Event"));
        console.log(...agendaEvents);
        displayAlert();
    });


    $('#show-events-btn').on('click', function (evt) {
        evt.preventDefault();
        $('.agenda-events').html('');
        if(agendaEvents.length){
            for(let agendaEvent of agendaEvents){
                $('.day[data-day="' + agendaEvent.date + '"]').find('.agenda-events').append(`<div class="event"><a href="#" class="agenda-event-link" data-serial="${agendaEvent.serial}">${agendaEvent.title} (${agendaEvent.serial})</a></div>`);
            }
        }
    });

    $(document).on('click', '.agenda-event-link', function(evt){
        evt.preventDefault();
        let serial = $(this).data('serial');
        agendaEvents = agendaEvents.filter((agendaEvent, index) => agendaEvent.serial !== serial);
        $('#show-events-btn').trigger('click');
    });

});

function addDayCell(currentDay){
    if(isSet == false && currentDay.format('dd') !== daysOfWeek[weekIndex]){
        daysElements += `<div class="day cell empty"></div>`;
        if(weekIndex == 6){
            weekIndex = 0;
        }else{
            weekIndex ++;
        }
        addDayCell(currentDay);
    }else{
        daysElements += `<div class="day cell ${currentDay.format('YYYY-MM-D') == now.format('YYYY-MM-D') ? 'active' : ''}" data-day="${currentDay.format()}"><div class="day-num"><a class="add-event-link" href="#">${currentDay.format('D')}</a></div><div class="agenda-events"></div></div>`;
        isSet = true;
    }
}

function displayAlert(){
    $('#alerts-container').append(`<div class="alert">L'event a bien été ajouté</div>`);
    setTimeout( () => $('.alert:first-child').remove(), 1500);
}


