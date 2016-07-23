$("document").ready(function(){
    function rechargerDiv() {
        $.ajax({
            type: "GET",
            url: "/rechargePub",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".content-tout-pub").prepend(data);
            }
        });
    }
    setInterval(rechargerDiv, 20000);

})