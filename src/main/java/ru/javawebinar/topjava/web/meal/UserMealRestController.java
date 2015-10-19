package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealRestController.class);

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals",params="action=delete", method=RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        service.delete(getId(request), LoggedUser.id());
        //model.addAttribute("meal",userMeal);
        return "redirect:meals";
    }


    @RequestMapping(value="/meals", params="action=update", method=RequestMethod.GET)
    public String editMealPage(HttpServletRequest request, Model model) {
        UserMeal userMeal=service.get(getId(request), LoggedUser.id());
        model.addAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value="/meals", method=RequestMethod.POST)
    public String editMeal(HttpServletRequest request) {
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        service.save(userMeal, LoggedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value="/meals", params="action=create", method=RequestMethod.GET)
    public String createMealPage(HttpServletRequest request, Model model) {
        UserMeal userMeal=new UserMeal();
        model.addAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value="/meals", params="action=filter", method= RequestMethod.POST)
    public String filter(HttpServletRequest request,Model model) {
        int userId = LoggedUser.id();

        LocalDate startDate = TimeUtil.parseLocalDate(request.getParameter("startDate"), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(request.getParameter("endDate"), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(request.getParameter("startTime"), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(request.getParameter("endTime"), LocalTime.MAX);
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        List<UserMealWithExceed> userMealWithExceeds = UserMealsUtil
                .getFilteredWithExceeded(service
                        .getBetweenDates(startDate, endDate, userId), startTime, endTime, LoggedUser.getCaloriesPerDay());
        model.addAttribute("mealList", userMealWithExceeds);
        return "mealList";
    }





    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<UserMealWithExceed> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll for User {}", userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public void update(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public UserMeal create(UserMeal meal) {
        int userId = LoggedUser.id();
        LOG.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = LoggedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        return UserMealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(startDate, endDate, userId), startTime, endTime, LoggedUser.getCaloriesPerDay()
        );
    }
}