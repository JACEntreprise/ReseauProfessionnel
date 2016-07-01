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
})