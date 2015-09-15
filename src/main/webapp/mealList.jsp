<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="ru.javawebinar.topjava.util.UserMealsUtil" %>
<%@ page import="ru.javawebinar.topjava.web.MealServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 13.09.2015
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MealList</title>
</head>
<body>
<%List<UserMealWithExceed> userMealWithExceedList= UserMealsUtil.getMealsWithExceed(UserMealsUtil.getMealList());
  Collections.sort(userMealWithExceedList, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
%>

<center>
  <h2>Meal list</h2>
  <a href="index.html">HOME</a>
  <br>
  <a href="${pageContext.request.contextPath}/newMeal.jsp">add new meal</a><br/>
  <br>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >

  <thead>
  <tr>
    <th width="100px">Description</th><th width="80px">Date</th><th width="80px">Time</th><th width="100px">Calories</th><th width="50px">actions</th>
  </tr>
  </thead>
  <tbody>
  <%for(UserMealWithExceed meal:userMealWithExceedList){%>
    <tr style="<%if(meal.isExceed()){%> background-color: pink<% } %>">
      <td> <%=meal.getDescription()%></td>
      <td> <%=meal.getDateTime().toLocalDate()%></td>
      <td> <%=meal.getDateTime().toLocalTime()%></td>
      <td> <%=meal.getCalories()%></td>
      <td>
        <a href="meals?action=edit&meal=<%=meal.getId()%>">Edit</a><br/>
        <a href="meals?action=delete&meal=<%=meal.getId()%>">Delete</a><br/>
      </td>
    </tr>
  <% } %>
  </tbody>
</table>
</center>


</body>
</html>
