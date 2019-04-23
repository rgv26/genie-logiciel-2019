$(function () {
    $("#cancel").click(function () {
        history.back();
    });
});

$(function () {
    $("#addFlight").click(function () {
        //Ajouter un vol
        var address = document.getElementById("address").value;
        var airportArr = document.getElementById("arrival")[document.getElementById("arrival").selectedIndex].value;
        var airportDep = document.getElementById("departure")[document.getElementById("departure").selectedIndex].value;
        var date = document.getElementById("date").value;
        var price = document.getElementById("price").value;
        var file = document.getElementById("file").files[0];
        var seat = document.getElementById("seatsInput").value;

        var time = document.getElementById("time").value;
        var hour = document.getElementById("hour").value;
        var minute = document.getElementById("minute").value;
        var flightDuration = hour + "." + minute;

        flightDuration = parseFloat(flightDuration);
        alert(address + '\n' + airportArr + '\n' + airportDep + '\n' + date + '\n' + price + '\n' + file + '\n' + flightDuration);
        var jsonData = '{ "departure": "' + airportDep + '", "arrival": "' + airportArr
                + '", "date": "' + document.getElementById("date").value + ' ' + time
                + '" , "appointenmtAdress": "' + address + '", "price": ' + price
                + ', "seats": ' + seat + ', "flightDuration" : "' + flightDuration
                + ', "image": "' + base64data + '" }';
        var reader = new FileReader();
        reader.readAsDataURL(file);
        var base64data = null;
        reader.onloadend = function () {
            base64data = reader.result;
            var jsonData = '{ "departure": "' + airportDep + '", "arrival": "' + airportArr +
                    '", "date": "' + document.getElementById("date").value + ' ' + time + '", ' +
                    '"appointenmtAdress": "' + address + '", "price": ' + price + ', "seats": ' + seat +
                    ', "flightDuration" : ' + flightDuration + ', "image": "' + base64data + '"}';
            $.ajax({
                url: "ws/flights",
                type: "POST",
                dataType: "xml/html/script/json", // expected format for response
                contentType: "application/json",
                data: jsonData,
                complete: function (data) {
                },
                error: function (da) {
                }
            });
        };


    });
});

function ListAirport()
{
    var c;
    var b = $.ajax({
        type: "GET",
        url: 'ws/airport/all/',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (data) {
            c = data;
        }
    });
    return c;
}

function time(min, max) {
    for (var d = min; d <= max; d++)
    {
        document.write("<option>" + d + "</option>");
    }
}

function airport() {
    var l = ListAirport();
    for (var i = 0; i < l.length; i++)
    {
        document.write("<option>" + l[i] + "</option>");
    }
}
