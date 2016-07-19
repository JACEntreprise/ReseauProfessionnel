$("document").ready(function(){
    $(".form-nouvelle").hide();
    $(".form-image").hide();
    $(".ajout-nouvelle").click(function(){
        $(".form-nouvelle").toggle();
        $(".form-image").hide();
    });
    $(".ajout-partage-photo").click(function(){
        $(".form-image").toggle();
        $(".form-nouvelle").hide();
    });

    $(".form-pub-new-partage").submit(function(){
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data:{
                contenu:$(this).find("#contenu").val()
            },
            dataType: 'html',
            success: function(data){
                $(".content-tout-pub").empty();
                $(".content-tout-pub").append(data);
            }
        });
        $(".form-nouvelle").hide();
        $(this).find("#contenu").val("");
        return false;
    });

    $('#pub-recharge').on('change','#urlImage', function (e) {
        var files = $(this)[0].files;

        if (files.length > 0) {
            // On part du principe qu'il n'y qu'un seul fichier
            // étant donné que l'on a pas renseigné l'attribut "multiple"
            var file = files[0];
            var nomFichier=file.name;
            var array=nomFichier.split('.');
            var extension=array[array.length -1];
            if(extension=='png' || extension=='jpg' || extension=='jpeg' ||extension=='gif'){
                $(".pub-image-show").removeClass("hidden");
                $(".pub-image-show").attr('src', window.URL.createObjectURL(file));
            }
        }
    });

    $('#content-formulaire-pub').on('change','#urlImage', function (e) {
        var files = $(this)[0].files;

        if (files.length > 0) {
            // On part du principe qu'il n'y qu'un seul fichier
            // étant donné que l'on a pas renseigné l'attribut "multiple"
            var file = files[0];
            var nomFichier=file.name;
            var array=nomFichier.split('.');
            var extension=array[array.length -1];
            if(extension=='png' || extension=='jpg' || extension=='jpeg' ||extension=='gif'){
                $(".img-pub-charge").removeClass("hidden");
                $(".contenu-img-label").addClass("hidden");
                $(".img-pub-charge").attr('src', window.URL.createObjectURL(file));
            }
        }
    });

    $("#pub-recharge").on("click",".lire-suite-pub",function(){
        var element=$(this).parent(".contenu-pub");
        $.ajax({
            type: "GET",
            url: $(this).data("target"),
            data:{
                ok: "ok"
            },
            dataType: 'html',
            success: function(data){
                element.empty();
                element.append(data);
            }
        });
        return false;
    });
})