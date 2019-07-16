function validate(id) {
    var result = true;
    var value = $(id);
    if (value.val() === '' || value.val() ==="other...") {
        alert('Field "' + value.attr('name').concat('" mustn\'t be empty.'));
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
    return validate($('#login'))
        && validate($('#password'));
}
function checkNewUser() {
    return validate($('#newLogin'))
        && validate($('#newPassword'))
        && validate($('#newPhone'));
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
function setValue(checkbox) {
    if ($(checkbox).is(':checked')) {
        $("#send").val("showAllAds");
    } else {
        $("#send").val("showActualAds");
    }
    return true;
}
$(document).ready(function(){
    let content = document.getElementById("content")
    let show = document.getElementById("showContent")
    let hide = document.getElementById("hideContent")

    show.addEventListener("click", () => {
        content.style.display = "block"
    })

    hide.addEventListener("click", () => {
        content.style.display = "none"
    })
});

