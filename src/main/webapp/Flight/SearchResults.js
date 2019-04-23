$(document).ready(function () {

    afficherVol();
});


function afficherVol() {
    var string = window.location.href;
    var url = new URL(string);
    var departure = url.searchParams.get("departure");
    var arrival = url.searchParams.get("arrival");
    $.ajax({
        url: 'ws/flights/search/' + departure + '/' + arrival,
        type: "GET",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json"
    }).done(function (result) {
        var len = result.length;
        alert(result[0].seats);
        for (var i = 0; i < len; i++) {
            var seats = result[i].seats;
            var departureAerodrom = result[i].departure;
            var arrivalAerodrom = result[i].arrival;
            var date = result[i].date;
            var flightDuration = result[i].flightDuration;
            var arrivalTime = result[i].arrivalTime;
            var price = result[i].price;
            if ('content' in document.createElement('template')) {

                // Instantiate the table with the existing HTML tbody
                // and the row with the template
                var template = document.querySelector('#productrow');

                // Clone the new row and insert it into the table
                var tbody = document.querySelector("tbody");
                var clone = document.importNode(template.content, true);
                var td = clone.querySelectorAll("td");
                td[0].textContent = departureAerodrom;
                td[1].textContent = arrivalAerodrom;
                td[2].textContent = price;
                td[3].textContent = seats;
                tbody.appendChild(clone);
            }
        }

    });
}