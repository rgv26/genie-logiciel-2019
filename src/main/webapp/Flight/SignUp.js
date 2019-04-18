
function changeForm() {
    var x = document.getElementById("typeUser").value;
    if (x === "User") {
        document.getElementById("pilot").style.visibility = 'hidden';
        document.getElementById("pilot").style.height = '0px';
    } else {
        if (x === "Pilot") {
            document.getElementById("pilot").style.visibility = 'visible';
            document.getElementById("pilot").style.height = 'auto';
        }
    }
}

$(function () {
    $("#cancel").click(function () {
        history.back();
    });
});

function checkEmail(email)
{
    var c;
    var b = $.ajax({
        type: "GET",
        url: 'ws/signup/email/' + email,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (data) {
            c = data;
        }
    });
    return c;
}


$(function () {
    $("#addUser").click(function () {
        //Ajouter un user ou pilot

        //Commun User et Pilot
        var sex;
        if (document.getElementById("homme").checked === true) {
            var sex = 'H';
        } else {
            var sex = 'F';
        }


        var firstname = document.getElementById("firstname").value;
        var lastname = document.getElementById("lastname").value;
        var email = document.getElementById("email").value;
        var check = checkEmail(email);
        if (!check) {
            alert("email déja utilisé");
            document.getElementById("email").value = "";
            return;
        }
        var password1 = document.getElementById("password1").value;
        var password2 = document.getElementById("password2").value;
        if (password1 !== password2) {
            alert("MDP incorrect");

            document.getElementById("password1").value = "";
            document.getElementById("password2").value = "";

            return;
        }
        var birthdate = document.getElementById("dateofbirth").value;
        var phone = document.getElementById("phone").value;
        //Fin commun
        alert(firstname + " " + lastname + " " + email + " " + password1 + " " + password2 + " " + birthdate + " " + phone);
        var x = document.getElementById("typeUser").value;
        if (x === "User") {
            var jsonData = '{ "email": "' + email + '", "password": "' + password1
                    + '", "firstName": "' + firstname + '" , "lastName": "' + lastname
                    + '", "sex": "' + sex + '", "birthdate": "' + birthdate
                    + '", "numTel": "' + phone + '" }';
            console.log(jsonData);
            $.ajax({
                url: "ws/signup/user",
                type: "POST",
                dataType: "xml/html/script/json", // expected format for response
                contentType: "application/json",
                data: jsonData,
                complete: function (data) {
                    alert(data);
                },
                error: function (da) {
                    alert(da);
                }
            });
        } else {
            if (x === "Pilot") {
                var exp = document.getElementById("experience").value;
                var qualif = document.getElementById("qualif").value;
                var file = document.getElementById("file").files[0];
                var hours = document.getElementById("hours").value;

                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    var base64data = reader.result;

                    var jsonData = '{ "email": "' + email
                            + '", "password": "' + password1
                            + '", "firstName": "' + firstname
                            + '" , "lastName": "' + lastname
                            + '", "sex": "' + sex
                            + '", "birthdate": "' + birthdate
                            + '", "numTel": "' + phone
                            + '", "experience": "' + exp
                            + '", "qualification": "' + qualif
                            + '", "licence": "' + base64data
                            + '", "flyingHours": "' + hours
                            + '" }';
                    console.log(jsonData);
                    $.ajax({
                        url: "ws/signup/pilot",
                        type: "POST",
                        dataType: "xml/html/script/json", // expected format for response
                        contentType: "application/json",
                        data: jsonData,
                        complete: function (data) {
                            alert(data);
                        },
                        error: function (da) {
                            alert(da);
                        }
                    });
                };
            }
        }
    });
});