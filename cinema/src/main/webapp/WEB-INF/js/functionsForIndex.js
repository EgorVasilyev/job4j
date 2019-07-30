$(document).ready(function () {
    showHalls();
    setInterval(function () {
        loadHall(currentHallNumber);
    }, 30000);
});

var currentHallNumber;

function loadHall(number) {
    currentHallNumber = number;
    $.ajax({
        type: "GET",
        url: "./hall",
        data: {
            "action": "loadHall",
            "number": number
        },
        complete: function (data) {
            cleanHall();
            var hall = JSON.parse(data.responseText);
            var countRanges = hall.countRanges;
            var countPlaces = hall.countPlaces;
            var trHead = $("#trhead");
            var tBody = $("#tbody");
            for (var range = 1; range <= countRanges; range++) {
                var result = "<tr>" +
                    "<td>" + range + "</td>";
                for (var placeForRange = 1; placeForRange <= countPlaces; placeForRange++) {
                    result +=
                        "<td>" +
                        "<h7 align=\"center\">" +
                        "<label>" +
                        "<font>" +
                        "<input type=\"checkbox\" onclick=\"checkSelect(this)\" id=\"place\" value=\"" +
                        range + "" + placeForRange + "\">" +
                        "Ряд " + range + ", Место " + placeForRange +
                        "</font>" +
                        "</label>" +
                        "</h7>" +
                        "</td>";
                }
                result += "</tr>";
                (tBody).append(result);
            }
            var resultTrHead = "<th style=\"width: 120px;\">Ряд / Место</th>";
            for (var place = 1; place <= countPlaces; place++) {
                resultTrHead += "<th>" + place + "</th>";
            }
            (trHead).append(resultTrHead);
            checkBlockedPlaces(hall);
        }
    });
}

function cleanHall() {
    $("#trhead").empty();
    $("#tbody").empty();
}

function checkBlockedPlaces(hall) {
    var blockedPlaces = hall.blockedPlaces;
    for (var i = 0; i < blockedPlaces.length; i++) {
        $('input:checkbox').each(function(){
            if ($(this).val() === blockedPlaces[i].toString())  {
                $(this).prop('disabled', true);
                $(this).parent().css('color', 'silver');
            }
        });
    }
}

function showHalls() {
    $(
        $.ajax({
            type: "GET",
            url: "./hall",
            data: "action=" + "showHalls",
            complete: function (data) {
                var halls = JSON.parse(data.responseText);
                var hall = $("#hall");
                for (var i = 0; i < halls.length; i++) {
                    (hall).append(
                        "<option value=\"" + halls[i].number + "\">" + halls[i].number + "</option>"
                    );
                }
            }
        })
    );
}

function getPairsAndGoPay() {
    var pairs = [];
    $('input:checkbox:checked').each(function(){
        pairs.push($(this).val())
    });
    sessionStorage.setItem("pairs", JSON.stringify(pairs));
    sessionStorage.setItem("hallNumber", currentHallNumber);
    window.location.href = "http://localhost:8080/cinema/payment.html";
}

checkobj = 0;
function checkSelect(obj) {
    if (obj.checked) {
        checkobj++;
    }
    else {
        checkobj--;
    }
    document.getElementsByName("submit_button")[0].disabled = checkobj <= 0;
}
