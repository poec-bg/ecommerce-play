$(document).ready(function(){
$(".image").fadeTo("slow", 0.5);
$(".image").hover(function(){
$(this).fadeTo("slow", 1);
},function(){
$(this).fadeTo("fast", 0.5);
});
});


