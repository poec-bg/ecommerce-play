(function(){
    $(function(){
        $('.add-product-json-link').on('click', function (event) {
            event.preventDefault();
            var $this = $(this);
            $.ajax({
                url: $this.attr('href'),
                type: 'GET'
            }).done(function(cart){
                var cartHTML = "<ul>";
                for(var product of cart.produits){
                    cartHTML += '<li class="cart-item">' + product.produit.description + '</li>';
                }
                cartHTML += "</ul>";
                $('#cart').html(cartHTML);
            }).fail(function(error){
                console.error(error);
            });
        });
    })
})();