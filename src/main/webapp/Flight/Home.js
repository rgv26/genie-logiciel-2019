/* global Cookies */

function moreButton() {
    var x = document.getElementById("plus").value;
    if (x === " - ") {
        document.getElementById("more").style.visibility = 'hidden';
        document.getElementById("more").style.height = '0px';
        document.getElementById("plus").value = " + ";
    } else {
        if (x === " + ") {
            document.getElementById("more").style.visibility = 'visible';
            document.getElementById("more").style.height = 'auto';
            document.getElementById("plus").value = " - ";
        }
    }
}

$(document).ready(function () {
    var content = "";
    if (Cookies.get("token")) {
        content = '<ul class="navbar-nav">' +
                '<li class="nav-item active">' +
                '<a class="nav-link" href="">' + Cookies.get('firstName') + '</a>' +
                '</li>' +
                '<li class="nav-item">' +
                '<a class="nav-link" href="" id="signOut" ">Sign Out</a>' +
                '</li>' +
                '</ul>';
    } else {
        content = '<ul class="navbar-nav">' +
                '<li class="nav-item active">' +
                '<a class="nav-link" href="SignUp.html">Sign Up</a>' +
                '</li>' +
                '<li class="nav-item">' +
                '<a class="nav-link" href="SignIn.html">Sign In</a>' +
                '</li>' +
                '</ul>';
    }

    $("#navbarNav").html(content);


    $("#signOut").click(function () {
        $.ajax({
            type: "GET",
            url: "ws/logout/user",
            async: false
        }).done(function () {
            location.reload();
        });

    });

});
function listFligth(departure, arrival)
{
    var c;
    var b = $.ajax({
        type: "GET",
        url: 'ws/flights/search/' + departure + '/' + arrival,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (data) {
            c = data;
        }
    });
    return c;
}
