package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 11.10.2015.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal,Integer> {

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:id ORDER BY m.dateTime DESC")
    List<UserMeal> findByUser(@Param("id")int id);


   /* @Modifying
    @Query("SELECT FROM Meal m WHERE m.user.id=:id")
    List<UserMeal> findAll( Sort sort);*/

    @Query("SELECT m FROM UserMeal m WHERE m.id=:mealId AND m.user.id=:userId")
    UserMeal findOne(@Param("mealId")int userMealId,@Param("userId")int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal m WHERE m.id=:mealId AND m.user.id=:userId")
    int delete(@Param("mealId")int userMealId,@Param("userId")int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:id and m.dateTime BETWEEN :startTime AND :endTime ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("startTime")LocalDateTime startDate,
                                    @Param("endTime")LocalDateTime endDate,
                                    @Param("id")int userId);


}
