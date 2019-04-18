
/* global Cookies */

$(document).ready(function () {
    var content = "";
    if (Cookies.get("token")) {
        content = '<ul class="nav navbar-nav navbar-right">' +
                '<li>' +
                '<a class="page-scroll" href="">' + Cookies.get('firstName') + '</a>' +
                '</li>' +
                '<li>' +
                '<a class="page-scroll" href="" id="signOut" ">Log Out</a>' +
                '</li>' +
                '</ul>';
    } else {
        content = '<ul class="nav navbar-nav navbar-right">' +
                '<li>' +
                '<a class="page-scroll" href="SignUp.html">Sign Up</a>' +
                '</li>' +
                '<li>' +
                '<a class="page-scroll" href="SignIn.html">Sign In</a>' +
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

function search() {
    var departure = document.getElementById("departure").value;
    var arrival = document.getElementById("arrival").value;
    location.replace("SearchResults.html?departure=" + departure + "&arrival=" + arrival);
}
