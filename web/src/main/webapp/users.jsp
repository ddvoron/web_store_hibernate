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
    <title>Редактирование пользователей</title>
</head>
<body>
<div class="dominator" onclick="hideForm();"></div>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <table id="modifyE">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Surname</td>
                <td>E-mail</td>
                <td>Login</td>
                <td>BlackList</td>
                <td>RegistrationDate</td>
                <td class="asdf"></td>
            </tr>
            <c:forEach var="point" items="${list}">
                <tr>
                    <td id="id${point.getIdUser()}">${point.getIdUser()}</td>
                    <td id="name${point.getIdUser()}">${point.getName()}</td>
                    <td id="surname${point.getIdUser()}">${point.getSurname()}</td>
                    <td id="email${point.getIdUser()}">${point.getEmail()}</td>
                    <td id="login${point.getIdUser()}">${point.getLogin()}</td>
                    <td id="blackList${point.getIdUser()}">${point.getBlackList()}</td>
                    <td id="registrationDate${point.getIdUser()}">${point.getRegistrationDate()}</td>
                    <td>
                        <div class="modifyB" onclick="modifyUser(${point.getIdUser()});">Редактировать</div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="megaDominator">
        <form id="editUsers" action="do?command=ModifyUsers" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2"><div class="tableName" >Редактирование пользователя</div></td>
                </tr>
                <tr>
                    <td><input type="hidden" id="id" name="ID"/></td>
                </tr>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" id="name" name="Name"/></td>
                </tr>
                <tr>
                    <td><label>Surname</label></td>
                    <td><input type="text" id="surname" name="Surname"/></td>
                </tr>
                <tr>
                    <td><label>E-mail</label></td>
                    <td><input type="text" id="email" name="Email"/></td>
                </tr>
                <tr>
                    <td><label>Login</label></td>
                    <td><input type="text" id="login" name="Login"/></td>
                </tr>
                <tr>
                    <td><label>BlackList</label></td>
                    <td><input type="text" id="blackList" name="BlackList"/></td>
                </tr>
                <!--Отправка формы-->
                <tr>
                    <td></td>
                    <td><div class="butn"><input class="button_ok" type="submit" value="Применить"/></div></td>
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
