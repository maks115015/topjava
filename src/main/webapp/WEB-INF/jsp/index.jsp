<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <span>
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
    <form method="post" action="users">
        <spring:message code="app.selectUser"/>: <select name="userId">
    <option value="100000" selected>User</option>
    <option value="100001">Admin</option>
</select>
    <button type="submit">Выбрать</button>
</form>
<ul>
    <li><a href="users">User List</a></li>
    <li><a href="meals">Meal List</a></li>
</ul>
</section>
<hr>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>