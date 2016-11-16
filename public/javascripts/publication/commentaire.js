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

    $("#la-pub").on("submit",".form-pub-new-pub",function(){
        var $this=$(this);
        $this.find('.loading').removeClass("hidden");
        $.ajax({
            type: "POST",
            url: $this.attr("action"),
            data:{
                contenu:$this.find("#contenu").val()
            },
            dataType: 'html',
            success: function(data){
                $("#la-pub").empty();
                $("#la-pub").append(data);
            }
        });
        return false;
    });
})