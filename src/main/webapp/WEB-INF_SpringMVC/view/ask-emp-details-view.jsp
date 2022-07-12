<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lexxx
  Date: 11.07.2022
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>

<body>

<h2>Dear Employee, please enter your details</h2>

<br>
<br>

<%--тег form метод get, при нажатии на кнопку submit должен произойти переход по адресу showDetails
переход по этому адресу перехватывает контроллер и указывает, какая веб страница должна отобразиться
employeeName - поле формы, хранящее значение, можно сказать что у поля есть переменная employeeName,
которая будет хранить все что вы введете

<form action="showDetails" method="get">
    <input type="text" name="employeeName"
           placeholder="Write your name"/>

отображает кнопку
    <input type="submit"/>
</form> --%>

<form:form action="showDetails" modelAttribute="employee">


    <%--Когда view отображается в браузере для каждой формы срабатывает getter - getName, getSalary и тл
    getName возвращает null, потому что мы используем noargs конструктор и геттеры принимают дефолтные значения
    после нажатия кнопки submit срабатывают сеттеры, поля атрибута Employee получают значения, которые мы будем отображать
    в show-emps-detail-view --%>
    Name <form:input path="name"/>
    <form:errors path="name"/> <%-- форма для ошибок валидации поля name --%>
    <br><br>
    Surname <form:input path="surname"/>
    <form:errors path="surname"/>
    <br><br>
    Salary <form:input path="salary"/>
    <form:errors path="salary"/>
    <br><br>
    Department <form:select path="department">
<%--    form:select–форма, предназначенная дляреализации выпадающего списка--%>

<%-- hardcoded реализация выбадающего списка
<form:option value="Information Technology" label="IT"/> &lt;%&ndash; label в выпадающем списке, в show-emp-details-view будет уже value &ndash;%&gt;--%>
<%--    <form:option value="Human Resourses" label="HR"/>--%>
<%--    <form:option value="Sales" label="Sales"/>--%>

    <%-- реализация выпадающего списка с помощью хешмапы departments в классе Employee --%>
    <form:options items="${employee.departments}"/>
    </form:select>
    <br><br>

<%-- hardcoded реализация radiobutton form:radiobutton–форма, предназначенная дляреализации radio button (переключатель)
BMW <form:radiobutton path="carBrand" value="BMW"/>--%>
<%--    Audi <form:radiobutton path="carBrand" value="Audi"/>--%>
<%--    MB <form:radiobutton path="carBrand" value="Mercedes-Benz"/>--%>
    Which car do you want? <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>
    <br><br>
    Foreign Language(s) <form:checkboxes path="languages" items="${employee.languageList}"/>
<%--    form:checkbox–форма, предназначенная дляреализации check box (флажок)
        EN <form:checkbox path="languages" value="English"/>--%>
<%--    DE <form:checkbox path="languages" value="Deutch"/>--%>
<%--    FR <form:checkbox path="languages" value="French"/>--%>
    <br><br>
    Phone Number <form:input path="phoneNumber"/>
    <form:errors path="phoneNumber"/>
    <br><br>
    Email <form:input path="email"/>
    <form:errors path="email"/>
    <br><br>

    <input type="submit" value="OK"> <%--кнопка с надписью OK--%>

</form:form>

</body>
</html>
