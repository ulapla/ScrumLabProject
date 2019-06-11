<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<jsp:include page="header.jsp"></jsp:include>
<form action="/app.recipe/add" method="post">
    <input type="text" name="name"/><br>
    <input type="text" name="description"/><br>
    <input type="number" name="preparationTime"/><br>
    <input type="text" name="preparation"/><br>
    <input type="text" name="ingredients"/><br>
    <input type="submit" value="Wyslij">
</form>
<jsp:include page="footer.jsp"></jsp:include>