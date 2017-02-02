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
    <title>Завершение сеанса</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <form id="loginForm" action="do?command=Logout" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2"><div class="tableName" >Завершить сеанс?</div></td>
                </tr>
                <!--Отправка формы-->
                <tr>
                    <td></td>
                    <td><div class="butn"><input class="button_ok" type="submit" value="Подтвердить"/></div></td>
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