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
    $('[data-toggle="tooltip"]').tooltip();
})