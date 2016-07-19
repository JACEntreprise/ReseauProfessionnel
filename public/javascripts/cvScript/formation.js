$("document").ready(function(){
    $('#profil-user').on("click",".bouton-entete-formation",function(){
        $.ajax({
            type: "GET",
            url: "/ajouter-formulaire-formation",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $("#profil-user").append(data);
            }
        });
    });

    $('#profil-user').on("click",".pencil-modifier-formation",function(){
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

    $("#profil-user").on("submit",".formulaire-formation",function(){
        var verif=true;
        var anneeDebut=$(this).find("#anneeDebut").val();
        var anneeFin=$(this).find("#anneeFin").val();
        if(anneeDebut!="annee" && anneeFin!="annee"){
            if(anneeDebut>=anneeFin){
                $(this).find(".form-group .error-duree-formation").removeClass("hidden");
                verif=false;
            }
        }else{
            $(this).find(".form-group .error-duree-formation").removeClass("hidden");
            verif=false;
        }
        if(verif==true){
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                data: {
                    type:               $(this).find("#type").val(),
                    etablissement:      $(this).find("#etablissement").val(),
                    diplome:            $(this).find("#diplome").val(),
                    anneeDebut:         anneeDebut,
                    anneeFin:           anneeFin,
                    resultat:           $(this).find("#resultat").val()
                },
                dataType: 'html',
                success: function(data){
                    $(".entete-info").empty();
                    $(".entete-info").append(data);
                }
            });
            $(this).parent(".content-form-content").remove();
        }
        return false;
    });

    $("#profil-user").on("submit",".formulaire-modifier-formation",function(){
        var verif=true;
        var anneeDebut=$(this).find("#anneeDebut").val();
        var anneeFin=$(this).find("#anneeFin").val();
        if(anneeDebut!="annee" && anneeFin!="annee"){
            if(anneeDebut>=anneeFin){
                $(this).find(".form-group .error-duree-formation").removeClass("hidden");
                verif=false;
            }
        }else{
            $(this).find(".form-group .error-duree-formation").removeClass("hidden");
            verif=false;
        }
        if(verif==true){
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                data: {
                    type:               $(this).find("#type").val(),
                    etablissement:      $(this).find("#etablissement").val(),
                    diplome:            $(this).find("#diplome").val(),
                    anneeDebut:         anneeDebut,
                    anneeFin:           anneeFin,
                    resultat:           $(this).find("#resultat").val()
                },
                dataType: 'html',
                success: function(data){
                    $(".entete-info").empty();
                    $(".entete-info").append(data);
                }
            });
            $(this).parent(".content-form-content").remove();
        }
        return false;
    });

    $("#profil-user").on("mouseenter",".item-formation",function(){
        $(this).find(".pencil-modifier-formation").removeClass("hidden");
    });
    $("#profil-user").on("mouseleave",".item-formation",function(){
        $(this).find(".pencil-modifier-formation").addClass("hidden");
    });

})