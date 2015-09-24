package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    public UserMeal save(UserMeal userMeal,int userid);

    public void delete(int id,int userid);

    public UserMeal get(int id, int userid);

    public Collection<UserMeal> getAll(int userid);

    public void update(UserMeal userMeal, int userid);
}
