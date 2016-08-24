(function(){
    $(function(){
        var prixN = document.getElementsByClassName("prixLgn");
        var i;
        var prixTotal = 0;
        for (i = 0; i < prixN.length; i++)
        {
          prixTotal = prixTotal + parseFloat(prixN[i].innerHTML);
        }
        prixTotal = Math.round(prixTotal * 100) / 100;
        document.getElementById("sousTotalPanier").innerHTML = prixTotal.toLocaleString() + " €";
    })
})();