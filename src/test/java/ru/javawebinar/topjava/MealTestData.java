package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static UserMeal userMeal1 =new UserMeal(100002,LocalDateTime.parse("2015-10-24 09:00:00"), "завтрак",1000);
    public static UserMeal userMeal2 =new UserMeal(100003,LocalDateTime.parse("2015-10-24 09:00:00"), "обед",1000);
    public static UserMeal userMeal3 =new UserMeal(100004,LocalDateTime.parse("2015-10-24 09:00:00"), "завтрак",1000);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
