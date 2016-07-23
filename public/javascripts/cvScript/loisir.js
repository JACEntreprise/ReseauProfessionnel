$("document").ready(function(){
    $('#profil-user').on("click",".bouton-entete-loisir",function(){
        $.ajax({
            type: "GET",
            url: "/ajouter-formulaire-loisir",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $("#profil-user").append(data);
            }
        });
    });

    $('#profil-user').on("click",".pencil-modifier-loisir",function(){
        $.ajax({
            type: "GET",
            url: $(this).attr("href"),
            data: "ok",
            dataType: 'html',
            success: function(data){
                $("#modifier").empty();
                $("#modifier").append(data);
            }
        });
        return false;
    });

    $("#profil-user").on("mouseenter",".content-form-content",function(){
            $(this).find(".supprimer-bloc").removeClass("hidden");
    });

    $("#profil-user").on("mouseleave",".content-form-content",function(){
            $(this).find(".supprimer-bloc").addClass("hidden");
    });

    $("#profil-user").on("click",".supprimer-bloc",function(){
            $(this).parent(".content-form-content").remove();
    });

    $("#profil-user").on("submit",".formulaire-loisir",function(){
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data: {
                libele:                $(this).find("#libele").val()
            },
            dataType: 'html',
            success: function(data){
                $(".entete-info").empty();
                $(".entete-info").append(data);
            }
        });
        $(this).parent(".content-form-content").remove();
        return false;
    });

    $("#profil-user").on("submit",".formulaire-modifier-loisir",function(){
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data: {
                libele:                $(this).find("#libele").val()
            },
            dataType: 'html',
            success: function(data){
                $(".entete-info").empty();
                $(".entete-info").append(data);
            }
        });
        $(this).parent(".content-form-content").remove();
        return false;
    });

    $("#profil-user").on("mouseenter",".item-formation",function(){
        $(this).find(".pencil-modifier-loisir").removeClass("hidden");
    });
    $("#profil-user").on("mouseleave",".item-formation",function(){
        $(this).find(".pencil-modifier-loisir").addClass("hidden");
    });
})