<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all" />
    <link rel="stylesheet" media="all" href="css/StyleReg.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <title>Авторизация</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <form id="loginForm" action="do?command=Login" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2"><div class="tableName" >Авторизация пользователя</div></td>
                </tr>
                <tr>
                    <td><label>Login</label></td>
                    <td><input type="text" id="login" name="Login" onfocus="hideLog();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="login1">Заполните поле!</div></td>
                    <td><div class="helper">Введите логин</div></td>
                </tr>
                <tr>
                    <td><label>Password</label></td>
                    <td><input name="Password" id="password" type="password" onfocus="hideLog();"/></td>
                </tr>
                <tr>
                    <td><div class="mes3" id="password1">Заполните поле!</div></td>
                    <td><div class="helper">Введите пароль</div></td>
                </tr>
                <!--Отправка формы-->
                <tr>
                    <td><div class="mail1"><a class="mail" href="do?command=SendMail">Забыли логин или пароль?</a></div></td>
                    <td><div class="butn"><input class="button_ok" type="button" onclick="event.preventDefault();sendFormLog();" value="Войти"/></div></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="footer">
        <div id="bottom_message"><p >${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
