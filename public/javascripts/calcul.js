var sum = function(){

}



$(function(){
    var total = 0;

    $('.product-price').each(function(){
       var productPrice = parseFloat($(this).text());
       total += productPrice;
    });

    $('#total').text(total);
});