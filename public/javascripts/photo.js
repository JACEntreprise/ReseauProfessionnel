$("document").ready(function(){
    $('#contenu-principal').on('change','#fichier', function (e) {
        var files = $(this)[0].files;

        if (files.length > 0) {
            // On part du principe qu'il n'y qu'un seul fichier
            // étant donné que l'on a pas renseigné l'attribut "multiple"
            var file = files[0];
            var nomFichier=file.name;
            var array=nomFichier.split('.');
            var extension=array[array.length -1];
            if(extension=='png' || extension=='jpg' || extension=='jpeg' ||extension=='gif'){
                $(".icon-image-profile").addClass("hidden");
                $(".profile-image-show").removeClass("hidden");
                $(".profile-image-show").attr('src', window.URL.createObjectURL(file));
                $(".bouton-upload-file").removeClass("hidden");
            }
            else{
                $(".bouton-upload-file").addClass("hidden");
            }
        }
    });

    $(".contenu-personnelle").on("submit","#form-complete-profil",function(){
        var verif=true;
        if($("#jour").val()!="jour" && $("#moi").val()!="moi" && $("#annee").val()!="annee"){
            $('.error-date').addClass("hidden");
        }
        else{
             $('.error-date').removeClass("hidden");
             verif=false;
        }
        if(/^ {0,}$/i.test($("#lieuDeNaissance").val())){
            $('.error-lieu').removeClass("hidden");
            verif=false;
        }
        else{
            $('.error-lieu').addClass("hidden");
        }
        if(/^[0-9]{5,}$/i.test($("#telephone").val())){
            $('.error-tel').addClass("hidden");
        }
        else{
            $('.error-tel').removeClass("hidden");
            verif=false;
        }

        if(/^ {0,}$/i.test($("#siteweb").val())){
            $('.error-site').removeClass("hidden");
            verif=false;

        }
        else{
            $('.error-site').addClass("hidden");
        }
        return verif;
    });

    $(".contenu-personnelle").on("submit","#form-complete-profil-entreprise",function(){
        var verif=true;
        if($("#jour").val()!="jour" && $("#moi").val()!="moi" && $("#annee").val()!="annee"){
            $('.error-date').addClass("hidden");
        }
        else{
             $('.error-date').removeClass("hidden");
             verif=false;
        }
        if(/^ {0,}$/i.test($("#domaine").val())){
            $('.error-domaine').removeClass("hidden");
            verif=false;
        }
        else{
            $('.error-domaine').addClass("hidden");
        }
        if(/^[0-9]{5,}$/i.test($("#telephone").val())){
            $('.error-tel').addClass("hidden");
        }
        else{
            $('.error-tel').removeClass("hidden");
            verif=false;
        }

        if(/^[0-9]{5,}$/i.test($("#adresse").val())){
            $('.error-adresse').addClass("hidden");
        }
        else{
            $('.error-adresse').removeClass("hidden");
            verif=false;
        }

        if(/^ {0,}$/i.test($("#siteweb").val())){
            $('.error-site').removeClass("hidden");
            verif=false;

        }
        else{
            $('.error-site').addClass("hidden");
        }
        return verif;
    });
    $('[data-toggle="tooltip"]').tooltip();
})