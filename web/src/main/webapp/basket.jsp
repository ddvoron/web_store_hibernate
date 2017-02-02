<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link href="css/StyleBasket.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <title>Корзина</title>
</head>
<body>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <c:if test="${user==null}">
            <div class="informer">${message}</div>
        </c:if>
        <c:forEach var="good" items="${list}">
            <div class="item" id="order${good.getIdData()}">
                <div class="item1">
                    <div class="item4">${good.getBrand()} ${good.getModel()}</div>

                    <div class="item7">
                        <c:if test="${good.getCatalogEntity().getIdCatalog()==1}">
                            <a class="a2" href="do?command=DescriptionFridge&id=${good.getIdData()}" target="_blank">Перейтик
                                описанию...</a>
                        </c:if>
                        <c:if test="${good.getCatalogEntity().getIdCatalog()==2}">
                            <a class="a2" href="do?command=DescriptionTv&id=${good.getIdData()}" target="_blank">Перейтик
                                описанию...</a>
                        </c:if>
                        <c:if test="${good.getCatalogEntity().getIdCatalog()==3}">
                            <a class="a2" href="do?command=DescriptionWasher&id=${good.getIdData()}" target="_blank">Перейтик
                                описанию...</a>
                        </c:if>
                        <c:if test="${good.getCatalogEntity().getIdCatalog()==4}">
                            <a class="a2" href="do?command=DescriptionMobile&id=${good.getIdData()}" target="_blank">Перейтик
                                описанию...</a>
                        </c:if>
                    </div>
                    <div class="item5">${good.getPrice()} BYN</div>
                </div>
                <div class="item2">
                    <div class="item3" onclick="deleteOrder(${good.getIdData()});">
                        Удалить
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="footer">
        <div id="bottom_message"><p>${message}</p></div>
        <div id="author_info"><p>All rights reserved © 2016-2017™ by Dmitry V</p></div>
    </div>
</div>
</body>
</html>
