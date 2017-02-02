<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
</div>