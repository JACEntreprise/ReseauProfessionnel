$("document").ready(function(){
    $('#profil-user').on("click",".bouton-entete-experience",function(){
        $.ajax({
            type: "GET",
            url: "/ajouter-formulaire-experience",
            data: "ok",
            dataType: 'html',
            success: function(data){
                $("#profil-user").append(data);
            }
        });
    });

    $('#profil-user').on("click",".pencil-modifier-experience",function(){
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

    $("#profil-user").on("submit",".formulaire-experience",function(){
        var verif=true;
        var etat=false;
        var anneeDebut=$(this).find("#anneeDebut").val();
        var anneeFin=$(this).find("#anneeFin").val();
        var moiDebut=$(this).find("#moiDebut").val();
        var moiFin=$(this).find("#moiFin").val();
        if(anneeDebut!="annee" && anneeFin!="annee" && moiDebut!="moi" && moiFin!="moi"){
            if(anneeDebut>anneeFin){
                $(this).find(".form-group .error-duree-experience").removeClass("hidden");
                verif=false;
            }else{
                if(anneeDebut==anneeFin){
                    if(moiDebut>moiFin){
                        $(this).find(".form-group .error-duree-experience").removeClass("hidden");
                        verif=false;
                    }
                }
            }
        }else{
            $(this).find(".form-group .error-duree-experience").removeClass("hidden");
            verif=false;
        }
        if(verif==true){
            var i=0;
            $(this).find("#etat:checked").each(function(){
                i++;
            })
            if(i!=0){
                etat=true;
            }
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                data: {
                    entreprise:                 $(this).find("#entreprise").val(),
                    lieu:                       $(this).find("#lieu").val(),
                    titre:                      $(this).find("#titre").val(),
                    anneeDebut:                 anneeDebut,
                    anneeFin:                   anneeFin,
                    moiDebut:                   moiDebut,
                    moiFin:                     moiFin,
                    etat:                       etat
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

    $("#profil-user").on("submit",".formulaire-modifier-experience",function(){
        var verif=true;
        var etat=false;
        var anneeDebut=$(this).find("#anneeDebut").val();
        var anneeFin=$(this).find("#anneeFin").val();
        var moiDebut=$(this).find("#moiDebut").val();
        var moiFin=$(this).find("#moiFin").val();
        if(anneeDebut!="annee" && anneeFin!="annee" && moiDebut!="moi" && moiFin!="moi"){
            if(anneeDebut>anneeFin){
                $(this).find(".form-group .error-duree-experience").removeClass("hidden");
                verif=false;
            }else{
                if(anneeDebut==anneeFin){
                    if(moiDebut>moiFin){
                        $(this).find(".form-group .error-duree-experience").removeClass("hidden");
                        verif=false;
                    }
                }
            }
        }else{
            $(this).find(".form-group .error-duree-experience").removeClass("hidden");
            verif=false;
        }
        if(verif==true){
            var i=0;
            $(this).find("#etat:checked").each(function(){
                i++;
            })
            if(i!=0){
                etat=true;
            }
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                data: {
                    entreprise:                 $(this).find("#entreprise").val(),
                    lieu:                       $(this).find("#lieu").val(),
                    titre:                      $(this).find("#titre").val(),
                    anneeDebut:                 anneeDebut,
                    anneeFin:                   anneeFin,
                    moiDebut:                   moiDebut,
                    moiFin:                     moiFin,
                    etat:                       etat
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
        $(this).find(".pencil-modifier-experience").removeClass("hidden");
    });
    $("#profil-user").on("mouseleave",".item-formation",function(){
        $(this).find(".pencil-modifier-experience").addClass("hidden");
    });
})