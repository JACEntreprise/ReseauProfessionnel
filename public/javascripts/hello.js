$("document").ready(function(){
    function rechargerDiv() {
        $.ajax({
            type: "GET",
            url: "/rechargePub",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".contenu-center-gauche").prepend(data);
            }
        });
    }
    //ssetInterval(rechargerDiv, 20000);
})