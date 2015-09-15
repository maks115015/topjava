<%@ page import="ru.javawebinar.topjava.util.UserMealsUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>New meal page</title>
</head>
<body>
<h1>Create new meal</h1>
<form action="meals?action=add" method=post>
  <table>
    <tbody>
    <tr>
      <td>Description</td>
      <td><input type="text" name="Description" value="" size=15 maxlength=20></td>
    </tr>
    <tr>
      <td>Date</td>
      <td><input type="text" name="Date" value="" size=15 maxlength=20></td>

    </tr>
    <tr>
      <td>Calories</td>
      <td><input type="text" name="Calories" value="" size=15 maxlength=20></td>

    </tr>
    <tr>
      <td><input type="submit" value="Create" /></td>
      <td></td>
      <td></td>
    </tr>
    </tbody>
  </table>
</form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
