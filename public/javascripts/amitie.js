$("document").ready(function(){
    $("#pub-recharge").on("click",".amitie-action",function(){
        $.ajax({
            type: "GET",
            url: $(this).attr("href"),
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".sugges").empty();
                $(".sugges").append(data);
            }
        });
        return false;
    });

    $("#pub-recharge").on("click",".amitie-action-ac-refus",function(){
        $.ajax({
            type: "GET",
            url: $(this).attr("href"),
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".demand").empty();
                $(".demand").append(data);
            }
        });
        return false;
    });

})