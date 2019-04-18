/* global Cookies */

if (Cookies.get('token'))
    location.replace("HomePage.html");

$(document).ready(function () {
    $("#signInForm").submit(function (event) {
        event.preventDefault();
        email = $(this).find("#email").val();
        password = $(this).find("#password").val();
        jsonData = '{"email" : "' + email + '", "password": "' + password + '"}';
        console.log(jsonData);

        $.ajax({
            type: "POST",
            url: "ws/login/user",
            contentType: "application/json",
            data: jsonData
        }).done(function (data) {
            location.replace("HomePage.html");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            $("#errorMessage").removeClass("d-none");
        });
    });

});