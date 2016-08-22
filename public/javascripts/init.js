var $document, $window, $body;

(function($){
    $document = $(document);
    $window = $(window);
    $body = $('body');

    $(function(){
        resetContentVisual();

        $document.on('click', '.vignettes', function(event) {
            event.preventDefault();
            var id = $(this).attr('id');
            var src = $(this).attr('src');
            $("#grande-image").attr('src', src);
            return false;
        });

        $('.thumbnail-visuel').each(function(){
            $(this).attr('src').toLowerCase();
        })
    });

    // Dimensionnement des images du bloc section du milieu
    function resetContentVisual(){
        $('.content-visual').each(function(){
            var $this = $(this);
            $this.css({'height' : $this.parents('.content-item').height() + "px"});
        });
    }

})(jQuery);