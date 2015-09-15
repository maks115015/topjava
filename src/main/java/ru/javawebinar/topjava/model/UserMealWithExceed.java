package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;
    protected final int id;

    protected final boolean exceed;

    public int getId() {
        return id;
    }

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, int id, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.id = id;

        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }


    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }
}
