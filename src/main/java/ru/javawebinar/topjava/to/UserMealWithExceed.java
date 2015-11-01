package ru.javawebinar.topjava.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */

//@JsonAutoDetect(fieldVisibility = NONE, getterVisibility = ANY, isGetterVisibility = NONE,setterVisibility = NONE )
public class UserMealWithExceed {
    protected final Integer id;

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    public UserMealWithExceed(@JsonProperty("id") Integer id,
                              @JsonProperty("dateTime") LocalDateTime dateTime,
                              @JsonProperty("description") String description,
                              @JsonProperty("calories") int calories,
                              @JsonProperty("exceed") boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this(null, dateTime, description, calories, exceed);
    }

    /*public UserMealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }*/

    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMealWithExceed)) return false;

        UserMealWithExceed that = (UserMealWithExceed) o;

        if (calories != that.calories) return false;
        if (exceed != that.exceed) return false;
        if (!id.equals(that.id)) return false;
        if (!dateTime.equals(that.dateTime)) return false;
        return description.equals(that.description);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + calories;
        result = 31 * result + (exceed ? 1 : 0);
        return result;
    }
}
