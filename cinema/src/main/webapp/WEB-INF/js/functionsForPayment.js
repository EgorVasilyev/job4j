var pairs;

var finishPrice;

$(document).ready(function () {
    pairs = JSON.parse(sessionStorage.getItem("pairs"));
    var tBody = $("#tbody");
    var result = "";
    pairs.forEach(function (value) {
        var range = value.toString().charAt(0);
        var place = value.toString().charAt(1);
        result += "<tr><td>Ряд " + range + ", Место " + place + "</td><td>500</td></tr>";
    });
    finishPrice = 500 * pairs.length;
    result += "<tr><td><strong>Итого:</strong></td><td><strong>" + finishPrice + "</strong></td></tr>";
    (tBody).append(result);
});

function toPay() {
    var fio = $("#fio").val();
    var phone = $("#phone").val();
    $.ajax({
        type: "POST",
        url: "./hall",
        data: {
            "fio": fio,
            "phone": phone,
            "hallNumber": sessionStorage.getItem("hallNumber"),
            "finishPrice": finishPrice,
            "blockedPlaces": JSON.stringify(pairs)
        },
        complete: function () {
            alert("Оплачено!");
            window.location.href = "http://localhost:8080/cinema/index.html";
        }
    });
}

function checkInput() {
    return validate($('#fio'))
        && validate($('#phone'));
}

function validate(id) {
    var result = true;
    var value = $(id);
    if (value.val() === '') {
        alert('Поле "' + value.attr('name').concat('" должно быть заполнено!'));
        result = false;
    }
    return result;
}