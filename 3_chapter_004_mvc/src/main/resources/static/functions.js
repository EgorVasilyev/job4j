function validate(id) {
    var result = true;
    var value = $(id);
    var temp = value.attr('name');
    if(value.attr('name')==='myLogin') {
        temp = 'login';
    }
    if(value.attr('name')==='myPassword') {
        temp = 'password';
    }
    if (value.val() === '' || value.val() ==="other...") {
        alert('Field "' + temp.concat('" mustn\'t be empty.'));
        result = false;
    }
    return result;
}
function validatePair(id1, id2) {
    var result = true;
    var value1 = $(id1);
    var value2 = $(id2);
    if (value1.val() === '' || (value1.val() ==="other..." && value2.val() === '' )) {
        alert('Field "' + value1.attr('name').concat('" mustn\'t be empty.'));
        result = false;
    }
    if ((value1.attr('name')==="year") && (value2.val()) && (!Date.parse(value2.val())))  {
        alert('Field "' + value1.attr('name').concat('" must be in the year format. For example: 2007'));
        result = false;
    }
    return result;
}
function checkAuthentication() {
    return validate($('#myLogin'))
        && validate($('#myPassword'));
}
function checkNewUser() {
    return validate($('#login'))
        && validate($('#password'))
        && validate($('#phone'));
}
function checkUser() {
    return validate($('#password'))
        && validate($('#phone'));
}
function checkAd() {
    var result = validate($('#model'))
        && validatePair($('#year'), $('#yearAddit'))
        && validatePair($('#body'), $('#bodyAddit'))
        && validatePair($('#color'), $('#colorAddit'))
        && validatePair($('#engine'), $('#engineAddit'))
        && validate($('#description'));
    if(result) {
        alert("Successfully!")
    }
    return result;
}
function showAdditionalField(select) {
    var value = $(select).val();
    var id = $(select).attr('id');
    if(value === "other...") {
        if(document.getElementById(id + "Addit") == null) {
            (document.getElementById(id + "Field")).innerHTML =
                "<input class=\"form-control\" type='text' placeholder='input your value' name='" +
                id + "Addit' id='" + id + "Addit'>";
        }
    } else {
        if(document.getElementById(id + "Addit") != null) {
            document.getElementById(id + "Addit").remove();
        }
    }
}
function setValueYesNo(checkbox, param) {
    if ($(checkbox).is(':checked')) {
        $(param).val("yes");
    } else {
        $(param).val("");
    }
}
function clearFilters() {
    document.getElementById('checkboxActual').checked = false;
    document.getElementById('checkboxCurrentDay').checked = false;
    document.getElementById('checkboxWithPhoto').checked = false;
    document.getElementById('byName').value = '';

    setValueYesNo($('#checkboxActual'), $('#actual'));
    setValueYesNo($('#checkboxCurrentDay'), $('#currentDay'));
    setValueYesNo($('#checkboxWithPhoto'), $('#withPhoto'));
}

