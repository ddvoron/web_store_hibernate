<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link rel="stylesheet" media="all" href="css/Style-index.css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <title>Главная</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content-index">
        <img src="img/st2.jpg"/>
        <div id="money">
            <iframe frameborder="0" height="131" marginheight="0" marginwidth="0" scrolling="no"
                    src="http://myfin.by/scripts/informer/informer.php" width="240"></iframe>
        </div>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
