$("document").ready(function(){
    $("#pub-recharge").on("submit",".form-pub-new-pub",function(){
        var id=$(this).find(".element-cache").val();
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data:{
                contenu:$(this).find("#contenu").val()
            },
            dataType: 'html',
            success: function(data){
                $("#pub-"+id).empty();
                $("#pub-"+id).append(data);
            }
        });
        $(this).find("#contenu").val("");
        return false;
    });
})