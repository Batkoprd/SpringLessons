<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lexxx
  Date: 11.07.2022
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Dear Employee, you are WELCOME!</h2>
<br>
<br>
<br>

<%--Your name: ${param.employeeName}--%>

<%--Your name: ${nameAttribute}  ${description} обращение к атрибуту из view--%>


<%--Во View обращение к значению идёт по имени атрибута и его полю, срабатывают геттеры поля--%>
Your name: ${employee.name}
<br>
Your surname: ${employee.surname}
<br>
Your salary: ${employee.salary}
<br>
Your department: ${employee.department}
<br>
Your car: ${employee.carBrand}
<br>
Language(s):
<%-- <ul> unordered list, работник может знать несколько языков, эти несколько языков во view выводятся с помощью c:forEach цикла, который требует неймспейс
lang временная переменная, принимающая значение всех элементов массива--%>
<ul>
    <c:forEach var="lang" items="${employee.languages}">
<%--  <li> list item  --%>
        <li> ${lang} </li>
    </c:forEach>
</ul>

Phone Number: ${employee.phoneNumber}
<br>
Email: ${employee.email}

</body>
</html>
