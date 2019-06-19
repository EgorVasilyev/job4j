function checkInput() {
    return validate($('#name'))
        && validate($('#login'))
        && validate($('#email'))
        && validate($('#password'));
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