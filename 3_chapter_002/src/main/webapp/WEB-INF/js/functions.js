function checkInput() {
    return validate($('#name'))
        && validate($('#login'))
        && validate($('#email'))
        && validate($('#password'))
        && validate($('#country'))
        && validate($('#city'));
}
function validate(id) {
    var result = true;
    var value = $(id);
    if (value.val() === '') {
        alert('Field "' + value.attr('name').concat('" mustn\'t empty.'));
        result = false;
    }
    return result;
}
function checkAuthentication() {
    return validate($('#login'))
        && validate($('#password'));
}
/**
 * Fills the <code>select</code> of cities getting
 * an ajax get query to the server
 */
function fillCities(param) {
    $.ajax({
        url: './jsonCity',
        method: 'GET',
        data: "countryId=" + param,
        complete: function(data) {
            var result = "";
            var cities = JSON.parse(data.responseText);
            for (var i = 0; i < cities.length; i++) {
                var city = cities[i];
                result +=
                    "<option value=\"" + city.id + "\">" + city.name + "</option>";
            }
            var table = document.getElementById("city");
            table.innerHTML = result;
        }
    });
}