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
})