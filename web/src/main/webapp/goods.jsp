<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" media="all" href="css/Style-menu.css"/>
    <link href="css/Style.css" rel="stylesheet" media="all"/>
    <link href="css/StyleReg.css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/order.js"></script>
    <title>Редактирование товаров</title>
</head>
<body>
<div class="dominator" onclick="hideFormGood();"></div>
<div id="container">
    <jsp:include page="menu.jsp"></jsp:include>
    <div id="content">
        <table id="modifyE">
            <tr>
                <td class="des3"></td>
                <td>ID</td>
                <td>Brand</td>
                <td>Model</td>
                <td>Price</td>
                <td>ReleaseDate</td>
                <td>Picture</td>
                <td>Department</td>
                <td>CreationDate</td>
                <td>Creator</td>
                <td>UpdateDate</td>
                <td>Updater</td>
            </tr>
            <c:forEach var="point" items="${list}">
                <tr id="element${point.getIdData()}">
                    <td id="chosenField"><input type="radio" name="chosen" value="${point.getIdData()}"/></td>
                    <td id="id${point.getIdData()}">${point.getIdData()}</td>
                    <td id="brand${point.getIdData()}">${point.getBrand()}</td>
                    <td id="model${point.getIdData()}">${point.getModel()}</td>
                    <td id="price${point.getIdData()}">${point.getPrice()}</td>
                    <td id="releaseDate${point.getIdData()}">${point.getReleaseDate()}</td>
                    <td id="picture${point.getIdData()}">${point.getPicture()}</td>
                    <td id="fk${point.getIdData()}">${point.getCatalogEntity().getIdCatalog()}</td>
                    <td id="creationDate${point.getIdData()}">${point.getCreationDate()}</td>
                    <td id="creator${point.getIdData()}">${point.getCreator()}</td>
                    <td id="updateDate${point.getIdData()}">${point.getUpdateDate()}</td>
                    <td id="updater${point.getIdData()}">${point.getUpdater()}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="actions">
            <div class="modifyC" onclick="addGood();">Добавить товар</div>
            <div class="modifyB" onclick="modifyGood();">Редактировать</div>
            <div class="modifyD" onclick="deleteGood();">Удалить</div>
        </div>
        <div id="pagination">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                ${page1==1?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=1\">1</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=1\">1</a></li>"}
                ${page1==2?
                        "<li><a class=\"active\" href=\"do?command=FridModifyGoodsge&page=2\">2</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=2\">2</a></li>"}
                ${page1==3?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=3\">3</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=3\">3</a></li>"}
                ${page1==4?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=4\">4</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=4\">4</a></li>"}
                ${page1==5?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=5\">5</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=5\">5</a></li>"}
                ${page1==6?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=6\">6</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=6\">6</a></li>"}
                ${page1==7?
                        "<li><a class=\"active\" href=\"do?command=ModifyGoods&page=7\">7</a></li>"
                        :
                        "<li><a href=\"do?command=ModifyGoods&page=7\">7</a></li>"}
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>
    <div class="megaDominator1">
        <form id="editGoods" action="do?command=ModifyGoods" method="post">
            <table class="registrationTable">
                <tr>
                    <td colspan="2">
                        <div class="tableName">Редактирование/добавление товара</div>
                    </td>
                </tr>
                <tr>
                    <td><input type="hidden" id="id" name="ID"/></td>
                </tr>
                <tr>
                    <td><label>Brand</label></td>
                    <td><input type="text" id="brand" name="Brand"/></td>
                </tr>
                <tr>
                    <td><label>Model</label></td>
                    <td><input type="text" id="model" name="Model"/></td>
                </tr>
                <tr>
                    <td><label>Price</label></td>
                    <td><input type="text" id="price" name="Price"/></td>
                </tr>
                <tr>
                    <td><label>ReleaseDate</label></td>
                    <td><input type="text" id="releaseDate" name="ReleaseDate"/></td>
                </tr>
                <tr>
                    <td><label>Picture</label></td>
                    <td><input type="text" id="picture" name="Picture"/></td>
                </tr>
                <tr>
                    <td><label>Department</label></td>
                    <td><input type="text" id="fk" name="Department"/></td>
                </tr>
                <!--Отправка формы-->
                <tr>
                    <td></td>
                    <td>
                        <div class="butn"><input class="button_ok" type="submit" value="Применить"/></div>
                    </td>
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
