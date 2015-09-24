package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());
    int userId=LoggedUser.getUserId();

    @Autowired
    private UserMealServiceImpl service;

    public Collection<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll(userId);
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public UserMeal create(UserMeal userMeal) {
        userMeal.setId(null);
        LOG.info("create " + userMeal);
        return service.save(userMeal, userId);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, userId);
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(userMeal,userId);
    }



}
