<%@ page import="ru.javawebinar.topjava.util.UserMealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.UserMeal" %>
<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>edit meal</title>
  <%int id=Integer.parseInt(request.getParameter("meal"));
    UserMeal userMeal=UserMealsUtil.getMealList().get(id);
  %>
</head>
<body>
<h1>edit meal page</h1>
<form action="meals?action=editMeal&id=<%=id%>" method=post>
  <table>
    <tbody>
    <tr>
      <td>Description</td>
      <td><input type="text" name="Description" value="<%=userMeal.getDescription()%>" size=15 maxlength=20></td>
    </tr>
    <tr>
      <td>Date</td>
      <td><input type="text" name="Date" value="<%=userMeal.getDateTime()%>" size=15 maxlength=20></td>

    </tr>
    <tr>
      <td>Calories</td>
      <td><input type="text" name="Calories" value="<%=userMeal.getCalories()%>" size=15 maxlength=20></td>

    </tr>
    <tr>
      <td><input type="submit" value="Edit" /></td>
      <td></td>
      <td></td>
    </tr>
    </tbody>
  </table>
</form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
