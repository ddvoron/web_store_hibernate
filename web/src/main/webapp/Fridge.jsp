<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.ui-slider.js"></script>
    <script type="text/javascript" src="js/jquery.main.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <title>Холодильники</title>
</head>
<body>
<div id="container">
    <div id="top">
        <ul class="menu">
            <li><a href=".">Главная</a></li>
            <li><a href="">Каталог</a>
                <ul>
                    <li><a href="do?command=Tv">Телевизоры</a></li>
                    <li><a href="do?command=Fridge">Холодильники</a></li>
                    <li><a href="do?command=Washer">Стиральные машины</a></li>
                    <li><a href="do?command=Mobile">Мобильные телефоны</a></li>
                </ul>
            </li>
            <li><a href="do?command=Basket">Корзина</a></li>
            <c:if test="${user.getRoleEntity().getIdRole()==2}">
                <li id="menu1"><a href="">Кабинет Администратора</a>
                    <ul>
                        <li><a href='do?command=ModifyUsers'>Пользователи</a></li>
                        <li><a href='do?command=ModifyGoods'>Товары</a></li>
                    </ul>
                </li>
            </c:if>
            ${user==null?
                    "<li id=\"menu2\"><a href=\"do?command=Login\">Войти</a></li> <li id=\"menu4\"><a href=\"do?command=Reg\">Регистрация</a></li>"
                    :
                    "<li id=\"menu3\"><a href=\"\">Личный кабинет</a><ul><li><a href=\"do?command=ModifyProfile\">Редактирование профиля</a></li><li><a href=\"do?command=Logout\">Завершить сеанс</a></li></ul></li>"
                    }
        </ul>
    </div>
    <div id="sidebar">
        <li class="menu">
            <ul>
                <li class="button cl"><a href="" class="box2">Каталог</a></li>
                <li class="dropdown">
                    <ul>
                        <li><a href="do?command=Tv">Телевизоры</a></li>
                        <li><a href="do?command=Fridge">Холодильники</a></li>
                        <li><a href="do?command=Washer">Стиральные машины</a></li>
                        <li><a href="do?command=Mobile">Мобильные телефоны</a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li class="menu">
            <ul>
                <li class="button cl"><a href="#" class="box3">Контакты</a></li>
                <li class="dropdown">
                    <ul>
                        <li>+375(33)397-33-33</li>
                        <li>+375(44)397-33-33</li>
                        <li>webstore@gmail.com</li>
                    </ul>
                </li>
            </ul>
        </li>
        <div id="cost">
            <div class="formCost">
                <label for="minCost">Цена: от</label> <input type="text" id="minCost" value="0"/>
                <label for="maxCost">до</label> <input type="text" id="maxCost" value="2000"/>
                <input id="commandHidden" type="hidden" value="Fridge"/>
            </div>
            <div class="sliderCont">
                <div id="slider"></div>
            </div>
        </div>
    </div>
    <div id="content">
        <div id="department">
            <div id="insideDepartment">
                <c:forEach var="good" items="${fridge}">
                    <div class="element">
                        <div class="picture1">
                            <img class="picture" src="img/fridge/${good.getPicture()}.jpg"/>
                        </div>
                        <div class="des1">
                            <div class="brand">${good.getBrand()} ${good.getModel()}</div>
                            <div class="des2">
                                Доставка:
                                Самовывоз, По городу - Бесплатно. А также Экспресс-доставка по Минску в течение 3 часов!
                                Подробнее на сайте., По области - Выберите свой населенный пункт и рассчитайте стоимость
                                доставки на сайте, По Беларуси - Выберите свой населенный пункт и рассчитайте стоимость
                                доставки на сайте
                                Оплата:
                                Наличный расчет, Банковская карта, Система «Расчет» (ЕРИП), Почтовый перевод
                            </div>
                            <div class="descriptionGood"><a class="a1"
                                                            href="do?command=DescriptionFridge&id=${good.getIdData()}"
                                                            target="_blank">Перейти к
                                описанию...</a></div>
                        </div>
                        <div class="order">
                            <div class="price">${good.getPrice()} BYN</div>
                            <div class="price1">
                                <div id="order${good.getIdData()}" class="buttonOrder"
                                     onclick="addOrder(${good.getIdData()});">Добавить в корзину
                                </div>
                            </div>
                            <div class="price0">
                                <div class="mes" id="mes${good.getIdData()}">Требуется авторизация</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    ${page1==1?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=1\">1</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=1\">1</a></li>"}
                    ${page1==2?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=2\">2</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=2\">2</a></li>"}
                    ${page1==3?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=3\">3</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=3\">3</a></li>"}
                    ${page1==4?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=4\">4</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=4\">4</a></li>"}
                    ${page1==5?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=5\">5</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=5\">5</a></li>"}
                    ${page1==6?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=6\">6</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=6\">6</a></li>"}
                    ${page1==7?
                            "<li><a class=\"active\" href=\"do?command=Fridge&page=7\">7</a></li>"
                            :
                            "<li><a href=\"do?command=Fridge&page=7\">7</a></li>"}
                    <li><a href="#">&raquo;</a></li>
                </ul>
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
