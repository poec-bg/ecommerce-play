(function (){

    $(function(){
        $(document).on('click', ".ripple-effect-trigger", function(e){
            var rippler = $(this).parents('.ripple-effect');
            // create .ink element if it doesn't exist
            var inkColor;
            if(rippler.find(".ink").length == 0) {
                if(typeof rippler.attr("data-inkcolor") !== undefined){
                    inkColor = rippler.attr("data-inkcolor");
                }else{
                    var bgColor = rgb2hex(rippler.css('background-color'));
                    if(typeof bgColor === undefined){
                        bgColor = "#ffffff";
                    }
                    inkColor = ColorLuminance(bgColor, -0.1);
                }
                rippler.append('<span class="ink" style="background:' + inkColor + '"></span>');
            }

            var ink = rippler.find(".ink");

            // prevent quick double clicks
            ink.removeClass("animate");

            // set .ink diameter
            if(!ink.height() && !ink.width())
            {
                var d = Math.max(rippler.outerWidth(), rippler.outerHeight());
                ink.css({height: d, width: d});
            }

            // get click coordinates
            var x = e.pageX - rippler.offset().left - ink.width()/2;
            var y = e.pageY - rippler.offset().top - ink.height()/2;

            // set .ink position and add class .animate
            ink.css({
                top: y+'px',
                left:x+'px'
            }).addClass("animate");
        });
    });

    function ColorLuminance(hex, lum) {

        // validate hex string
        hex = String(hex).replace(/[^0-9a-f]/gi, '');
        if (hex.length < 6) {
            hex = hex[0]+hex[0]+hex[1]+hex[1]+hex[2]+hex[2];
        }
        lum = lum || 0;

        // convert to decimal and change luminosity
        var rgb = "#", c, i;
        for (i = 0; i < 3; i++) {
            c = parseInt(hex.substr(i*2,2), 16);
            c = Math.round(Math.min(Math.max(0, c + (c * lum)), 255)).toString(16);
            rgb += ("00"+c).substr(c.length);
        }

        return rgb;
    }

    var hexDigits = new Array
    ("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f");

    //Function to convert hex format to a rgb color
    function rgb2hex(rgb) {
        if(rgb.indexOf("rgba") > -1){
            rgb = rgb.replace("rgba", "rgb");
            rgb = rgb.substr(0, rgb.lastIndexOf(",")) + ")";
        }
        rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
    }

    function hex(x) {
        return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
    }
})();
