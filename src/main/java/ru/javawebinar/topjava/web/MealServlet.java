package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("action").equals("add")){
            this.add(request, response);}

        if(request.getParameter("action").equals("editMeal")){
            int id=Integer.parseInt(request.getParameter("id"));
            this.edit(request, response,id);
        }
        response.sendRedirect("mealList.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       try {
           if (request.getParameter("action").equals("delete")){
               UserMealsUtil.delete(Integer.parseInt(request.getParameter("meal")));
               response.sendRedirect("mealList.jsp");
           }

           else if(request.getParameter("action").equals("edit")){
                int id=Integer.parseInt(request.getParameter("meal"));
               request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
           } else {
               response.sendRedirect("mealList.jsp");
           }
       }catch(Exception e){}



    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        String description=request.getParameter("Description");
        String date=request.getParameter("Date");
        int calories =Integer.parseInt(request.getParameter("Calories"));
        UserMealsUtil.mealsCreateAndProceed(LocalDateTime.now(), description, calories);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response,int id) throws IOException{
        response.setStatus(200);
        String description=request.getParameter("Description");
        LocalDateTime date= LocalDateTime.now();
        int calories =Integer.parseInt(request.getParameter("Calories"));
        UserMealsUtil.getMealList().get(id).setCalories(calories);
        UserMealsUtil.getMealList().get(id).setDescription(description);
        UserMealsUtil.getMealList().get(id).setDateTime(date);
    }


}
