var styles = [
    {
        "elementType": "geometry",
        "stylers": [
            {
                "hue": "#ff4400"
            },
            {
                "saturation": -68
            },
            {
                "lightness": -4
            },
            {
                "gamma": 0.72
            }
        ]
    },
    {
        "featureType": "road",
        "elementType": "labels.icon"
    },
    {
        "featureType": "landscape.man_made",
        "elementType": "geometry",
        "stylers": [
            {
                "hue": "#0077ff"
            },
            {
                "gamma": 3.1
            }
        ]
    },
    {
        "featureType": "water",
        "stylers": [
            {
                "hue": "#00ccff"
            },
            {
                "gamma": 0.44
            },
            {
                "saturation": -33
            }
        ]
    },
    {
        "featureType": "poi.park",
        "stylers": [
            {
                "hue": "#44ff00"
            },
            {
                "saturation": -23
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.text.fill",
        "stylers": [
            {
                "hue": "#007fff"
            },
            {
                "gamma": 0.77
            },
            {
                "saturation": 65
            },
            {
                "lightness": 99
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "labels.text.stroke",
        "stylers": [
            {
                "gamma": 0.11
            },
            {
                "weight": 5.6
            },
            {
                "saturation": 99
            },
            {
                "hue": "#0091ff"
            },
            {
                "lightness": -86
            }
        ]
    },
    {
        "featureType": "transit.line",
        "elementType": "geometry",
        "stylers": [
            {
                "lightness": -48
            },
            {
                "hue": "#ff5e00"
            },
            {
                "gamma": 1.2
            },
            {
                "saturation": -23
            }
        ]
    },
    {
        "featureType": "transit",
        "elementType": "labels.text.stroke",
        "stylers": [
            {
                "saturation": -64
            },
            {
                "hue": "#ff9100"
            },
            {
                "lightness": 16
            },
            {
                "gamma": 0.47
            },
            {
                "weight": 2.7
            }
        ]
    }
]


function initMap(){
    if($('#map_canvas').length > 0){
        google.maps.event.addDomListener(window, 'load', initialize);
    }
}

function initialize() {
    var description = "<div id='map-marker'>Ma société<br>25 Rue de la République<br>75000 Paris</div>";
    var latitude = "49.4744296";
    var longitude = "1.1079211";


    var styledMap = new google.maps.StyledMapType(styles, {name: "Styled Map"});
    var map_canvas = document.getElementById('map_canvas');
    var myLatlng = new google.maps.LatLng(latitude, longitude);
    var map_options = {
        center: myLatlng,
        zoom: 15,
        mapTypeControlOptions: {
            mapTypeIds: [google.maps.MapTypeId.ROADMAP, 'map_style']
        }
    }
    var map = new google.maps.Map(map_canvas, map_options);

    map.mapTypes.set('map_style', styledMap);
    map.setMapTypeId('map_style');

    var contentString = description;
    var infowindow = new google.maps.InfoWindow({
        content: contentString,
        boxClass: "infoBox"
    });

    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map
    });

    infowindow.open(map,marker);
    var $mapMarkerContainer = $('#map-marker').parent().parent().parent().parent();
    if(!$mapMarkerContainer.hasClass('map-marker-container')){
        $mapMarkerContainer.addClass('map-marker-container');
    }

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map,marker);
    });
}