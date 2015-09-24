package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal,int userId);

    void delete(int id,int userId);

    UserMeal get(int id,int userId);

    Collection<UserMeal> getAll(int userId);
}
