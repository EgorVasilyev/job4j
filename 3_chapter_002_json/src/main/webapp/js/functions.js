//валидация
function checkInput() {
    return validate($('#name'))
        & validate($('#lastname'))
        & validate($('#description'));
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
//добавляет строку
function addRow() {
    var name = $('#name').val();
    var lastname = $('#lastname').val();
    var sex = this.document.getElementById("male").checked ?
        this.document.getElementById("male").value : this.document.getElementById("female").value ;
    var description = $('#description').val();

    //получаем ссылку на последний элемент в таблице.
    //и после него добавляем html
    if (checkInput()) {
        $('#table tr:last').after('<tr>' +
            '<td>' + name +'</td>' +
            '<td>' + lastname +'</td>' +
            '<td>' + sex +'</td>' +
            '<td>' + description +'</td>' +
            '</tr>');
    }
}
//добавляет строку и новый объект в хранилище
function addUser() {
    if (checkInput()) {
        var jsonUser = {
            name: $('#name').val(),
            lastname: $('#lastname').val(),
            sex: this.document.getElementById("male").checked ?
                this.document.getElementById("male").value : this.document.getElementById("female").value,
            description: $('#description').val()
        };
        $(
            $.ajax({
                type: "POST",
                url: "./json",
                dataType: 'json',
                data: JSON.stringify(jsonUser),
                contentType: 'text/json',
                mimeType: 'text/json',
                complete: function (data) {
                    var user = JSON.parse(data.responseText);
                    $('#table tr:last').after('<tr>' +
                        '<td>' + user.name +'</td>' +
                        '<td>' + user.lastname +'</td>' +
                        '<td>' + user.sex +'</td>' +
                        '<td>' + user.description +'</td>' +
                        '</tr>');
                }
            })
        );
    }
}