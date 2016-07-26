$("document").ready(function(){
    function rechargerIcon() {
        $.ajax({
            type: "GET",
            url: "/recharge-icon-comment",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".compte-comment-notification").empty();
                $(".compte-comment-notification").append(data);
            }
        });

        $.ajax({
            type: "GET",
            url: "/recharge-icon-user",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $(".compte-user-notification").empty();
                $(".compte-user-notification").append(data);
            }
        });
    }
    setInterval(rechargerIcon, 20000);
    $(".entete-page-personnelle-haut").on("mouseenter",".element-entete-rouge",function(){
        var $this=$(this);
        var $content_icon=$this.find(".content-icon");
        $content_icon.removeClass("hidden");
        $this.find(".loading").removeClass("hidden");
        $.ajax({
            type: "GET",
            url: $this.data('target'),
            data: "ok",
            dataType: 'html',
            success: function(data){
                $this.find(".content-icon-new").append(data);
                $content_icon.find('.content-icon-new').removeClass("hidden");
                $this.find(".loading").addClass("hidden");
            }
        });
    });

    $(".entete-page-personnelle-haut").on("mouseleave",".element-entete-rouge",function(){
       var $this=$(this);
       var $content_icon=$this.find(".content-icon");
       $content_icon.addClass("hidden");
       $content_icon.find('.content-icon-new').addClass("hidden");
       $this.find(".loading").addClass("hidden");
       $this.find(".content-icon-new").empty();
    });

    $("#contenu-principal").on("click",".commentaire-non-lu-pub-notification",function(){
        $.ajax({
            type: "GET",
            url: $(this).attr("target"),
            data: "ok",
            dataType: 'html',
            success: function(data){
                $("#contenu-notification").empty();
            }
        });
    });

})