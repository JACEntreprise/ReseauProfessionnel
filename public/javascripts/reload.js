$("document").ready(function(){
    function rechargerIcon() {
        $.ajax({
            type: "GET",
            url: "/rechargeTout",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".groupe-icon-recharge").empty();
                $(".groupe-icon-recharge").append(data);
            }
        });
    }
    setInterval(rechargerIcon, 20000);

})