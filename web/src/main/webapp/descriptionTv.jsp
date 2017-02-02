<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link rel="stylesheet" media="all" href="css/StyleTable.css"/>
    <link href="css/default.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="js/order.js"></script>
    <script src="js/mobilyslider.js" type="text/javascript"></script>
    <script src="js/init.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <title>Подробное описание</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <div id="content_descriptionF">
            <div id="inside">
                <div id="brandModel">${data.getBrand()} ${data.getModel()}</div>
                <div id="forTable1">
                    <table id="info1">
                        <c:forEach var="point" items="${list}">
                            <tr>
                                <td>${point.getTitle()}</td>
                                <td>${point.getValue()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div id="orderDuplicate">
                    <table>
                        <tr>
                            <td id="anotherTwo">
                                Стоимость: ${data.getPrice()} BYN
                            </td>
                        </tr>
                        <tr>
                            <td id="anotherOne">
                                <div id="order" class="buttonOrder" onclick="addOrderAddT(${data.getIdData()});">Добавить в
                                    корзину
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="mes" id="mes">Требуется авторизация</div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="warranty">Информация о товаре предоставлена для ознакомления и не является публичной офертой.
                    Информация о внешнем виде, характеристиках и комплектации товара обновляется с не-
                    которой временной задержкой. Просим вас отнестись с пониманием к данному факту и
                    заранее приносим извинения за возможные неточности в описании и фотографиях товара.
                    С целью предоставления достоверной информации - уточняйте, пожалуйста, все важные
                    для Вас параметры товары у консультанта при оформлении заказа.
                </div>
            </div>
            <div class="slider slider1">
                <div class="sliderContent">
                    <div class="item">
                        <img class="imgD" src="img/tv/${data.getPicture()}-1.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/tv/${data.getPicture()}-2.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/tv/${data.getPicture()}-3.jpg" alt=""/>
                    </div>
                    <div class="item">
                        <img class="imgD" src="img/tv/${data.getPicture()}-4.jpg" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
