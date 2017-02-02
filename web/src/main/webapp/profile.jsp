<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link href="css/StyleReg.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <title>Редактирование профиля</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <form id="registrationForm" action="do?command=ModifyProfile" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2"><div class="tableName" >Редактирование профиля</div></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" id="name" name="name" value="${user.getName()}" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="name1">Заполните поле!</div>
                        <div class="mes4" id="name2">Неверный формат!</div></td>
                    <td><div class="helper">Введите Ваше имя</div></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td><input type="text" id="surname" name="surname" value="${user.getSurname()}" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="surname1">Заполните поле!</div>
                        <div class="mes4" id="surname2">Неверный формат!</div></td>
                    <td><div class="helper">Введите Вашу фамилию</div></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="text" id="email" name="email" value="${user.getEmail()}" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="email1">Заполните поле!</div>
                        <div class="mes4" id="email2">Неверный формат!</div></td>
                    <td><div class="helper">Введите Ваш электронный адрес</div></td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td><input type="text" id="login" name="login" value="${user.getLogin()}" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="login1">Заполните поле!</div>
                        <div class="mes4" id="login2">Неверный формат!</div></td>
                    <td><div class="helper">Введите логин</div></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" id="password" name="password" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="password1">Заполните поле!</div>
                        <div class="mes4" id="password2">Неверный формат!</div></td>
                    <td><div class="helper">Введите пароль</div></td>
                </tr>
                <tr>
                    <td><label>Confirm password</label></td>
                    <td><input type="password" id="passwordRepeat" name="PasswordRepeat" onfocus="hide();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="passwordRepeat1">Заполните поле!</div>
                        <div class="mes4" id="passwordRepeat2">Неверный формат!</div>
                        <div class="mes4" id="passwordRepeat3">Повторите верно!</div></td>
                    <td><div class="helper">Введите пароль снова</div></td>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="butn"><input class="button_ok" type="button" onclick="event.preventDefault();sendFormReg();" value="Сохранить"/></div></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
