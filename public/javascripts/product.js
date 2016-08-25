(function(){
    $('#formAjoutPanier').on('submit', function(event) {
        event.preventDefault();
        var $this = $(this);
        $.ajax({
            url: $this.attr('action'),
            type: "POST",
            data: $this.serialize()
        }).done(function(resultat){
            if(resultat == true){
                document.getElementById("formAjoutPanier-resOK").removeClass("hidden");
                document.getElementById("formAjoutPanier-resN").addClass("hidden");
            }
            else{
                document.getElementById("formAjoutPanier-resOK").addClass("hidden");
                document.getElementById("formAjoutPanier-resN").removeClass("hidden");
            }
        }).fail(function(error){
            console.error(error);
            document.getElementById("formAjoutPanier-resOK").addClass("hidden");
            document.getElementById("formAjoutPanier-resN").removeClass("hidden");
        });
    });
})();