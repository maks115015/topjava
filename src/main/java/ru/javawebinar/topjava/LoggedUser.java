package ru.javawebinar.topjava;

import org.springframework.stereotype.Component;

/**
 * GKislin
 * 06.03.2015.
 */
@Component
public class LoggedUser {
    static Integer userId=1;

    public LoggedUser() {
    }

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        LoggedUser.userId = userId;
    }
}
