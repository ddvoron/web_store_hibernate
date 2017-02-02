function hideElement(id) {
    document.getElementById("mes" + id).style.visibility = "hidden";
}

function hideElementD() {
    document.getElementById("mes").style.visibility = "hidden";
}

function addOrder(id) {
    $.post('do?command=Fridge', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes" + id).style.visibility = "visible";
                setTimeout("hideElement(" + id + ")", 1500);
            } else {
                $('#order' + id).html('Добавлено');
            }
        });
}

function addOrderAdd(id) {
    $.post('do?command=DescriptionFridge', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes").style.visibility = "visible";
                setTimeout(hideElementD, 1500);
            } else {
                $('#order').html('Добавлено');
            }
        });
}

function addOrderM(id) {
    $.post('do?command=Mobile', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes" + id).style.visibility = "visible";
                setTimeout("hideElement(" + id + ")", 1500);
            } else {
                $('#order' + id).html('Добавлено');
            }
        });
}

function addOrderAddM(id) {
    $.post('do?command=DescriptionMobile', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes").style.visibility = "visible";
                setTimeout(hideElementD, 1500);
            } else {
                $('#order').html('Добавлено');
            }
        });
}

function addOrderW(id) {
    $.post('do?command=Washer1', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes" + id).style.visibility = "visible";
                setTimeout("hideElement(" + id + ")", 1500);
            } else {
                $('#order' + id).html('Добавлено');
            }
        });
}

function addOrderAddW(id) {
    $.post('do?command=DescriptionWasher', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes").style.visibility = "visible";
                setTimeout(hideElementD, 1500);
            } else {
                $('#order').html('Добавлено');
            }
        });
}

function addOrderT(id) {
    $.post('do?command=Tv', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes" + id).style.visibility = "visible";
                setTimeout("hideElement(" + id + ")", 1500);
            } else {
                $('#order' + id).html('Добавлено');
            }
        });
}

function addOrderAddT(id) {
    $.post('do?command=DescriptionTv', {neID: id},
        function (data) {
            if (data.indexOf('Для работы с корзиной требуется авторизация...') + 1) {
                document.getElementById("mes").style.visibility = "visible";
                setTimeout(hideElementD, 1500);
            } else {
                $('#order').html('Добавлено');
            }
        });
}

function deleteOrder(id) {
    $.post('do?command=Basket', {neID: id},
        function (data) {
            if (data.indexOf('Товар  удален из корзины')) {
                $('#order' + id).hide(500);
            } else {
                document.getElementById("mes2").style.visibility = "visible";
            }
        });
}

function deleteGood() {
    var id=$('input[name="chosen"]:checked').val();
    if(id!=null) {
        $.post('do?command=ModifyGoods', {ID: id * (-1)},
            function (data) {
                if (data.indexOf('Товар успешно удален')) {
                    $('#element' + id).hide(500);
                } else {
                }
            })
    };
}

function sendFormReg() {
    var nameReg = /^[а-яёЁА-Яa-zA-z]+$/;
    var emailReg = /^([a-zA-Z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})$/;
    var loginReg = /^[.а-яёЁА-Яa-zA-Z0-9_-]{5,15}$/;
    if ($('#name').val() == "") {
        document.getElementById("name1").style.visibility = "visible";
    } else if ($('#surname').val() == "") {
        document.getElementById("surname1").style.visibility = "visible";
    } else if ($('#email').val() == "") {
        document.getElementById("email1").style.visibility = "visible";
    } else if ($('#login').val() == "") {
        document.getElementById("login1").style.visibility = "visible";
    } else if ($('#password').val() == "") {
        document.getElementById("password1").style.visibility = "visible";
    } else if ($('#passwordRepeat').val() == "") {
        document.getElementById("passwordRepeat1").style.visibility = "visible";
    } else {
        if (!nameReg.test($('#name').val())) {
            document.getElementById("name2").style.visibility = "visible";
        } else if (!nameReg.test($('#surname').val())) {
            document.getElementById("surname2").style.visibility = "visible";
        } else if (!emailReg.test($('#email').val())) {
            document.getElementById("email2").style.visibility = "visible";
        } else if (!loginReg.test($('#login').val())) {
            document.getElementById("login2").style.visibility = "visible";
        } else if (!loginReg.test($('#password').val())) {
            document.getElementById("password2").style.visibility = "visible";
        } else if (!loginReg.test($('#passwordRepeat').val())) {
            document.getElementById("passwordRepeat2").style.visibility = "visible";
        } else {
            if (($('#password').val()) == ($('#passwordRepeat').val())) {
                $('#registrationForm').submit();
            } else {
                document.getElementById('passwordRepeat3').style.visibility = "visible";
            }
        }
    }
}

function sendFormLog() {
    if ($('#login').val() == "") {
        document.getElementById("login1").style.visibility = "visible";
    } else if ($('#password').val() == "") {
        document.getElementById("password1").style.visibility = "visible";
    } else {
        $('#loginForm').submit();
    }
}

function hide() {
    $('#name1').css({'visibility': 'hidden'});
    $('#surname1').css({'visibility': 'hidden'});
    $('#email1').css({'visibility': 'hidden'});
    $('#login1').css({'visibility': 'hidden'});
    $('#password1').css({'visibility': 'hidden'});
    $('#passwordRepeat1').css({'visibility': 'hidden'});
    $('#name2').css({'visibility': 'hidden'});
    $('#surname2').css({'visibility': 'hidden'});
    $('#email2').css({'visibility': 'hidden'});
    $('#login2').css({'visibility': 'hidden'});
    $('#password2').css({'visibility': 'hidden'});
    $('#passwordRepeat2').css({'visibility': 'hidden'});
    $('#passwordRepeat3').css({'visibility': 'hidden'});
}

function hideLog() {
    document.getElementById("login1").style.visibility = "hidden";
    document.getElementById("password1").style.visibility = "hidden";
}

function modifyUser(id) {
    $('.dominator').css({'display': 'block'});
    $('.megaDominator').css({'display': 'block'});
    $('#id').val($('#id' + id).html());
    $('#name').val($('#name' + id).html());
    $('#surname').val($('#surname' + id).html());
    $('#email').val($('#email' + id).html());
    $('#login').val($('#login' + id).html());
    $('#blackList').val($('#blackList' + id).html());
}

function hideForm() {
    $('.dominator').css({'display': 'none'});
    $('.megaDominator').css({'display': 'none'});
}

function modifyGood() {
    var id=$('input[name="chosen"]:checked').val();
    if(id!=null) {
        $('.dominator').css({'display': 'block'});
        $('.megaDominator1').css({'display': 'block'});
        $('#id').val($('#id' + id).html());
        $('#brand').val($('#brand' + id).html());
        $('#model').val($('#model' + id).html());
        $('#price').val($('#price' + id).html());
        $('#releaseDate').val($('#releaseDate' + id).html());
        $('#picture').val($('#picture' + id).html());
        $('#fk').val($('#fk' + id).html());
    }
}

function addGood() {
    $('.dominator').css({'display': 'block'});
    $('.megaDominator1').css({'display': 'block'});
    $('#id').val(0);
}

function hideFormGood() {
    $('.dominator').css({'display': 'none'});
    $('.megaDominator1').css({'display': 'none'});
}