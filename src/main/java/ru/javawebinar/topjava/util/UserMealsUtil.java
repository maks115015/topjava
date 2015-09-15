package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500)
        );
//        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        filteredMealsWithExceeded.forEach(System.out::println);
//
//        System.out.println(getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }


        public static List<UserMeal> mealList = new ArrayList<>();
              static {

                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500));
                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500));
                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 28,20,0), "Ужин", 500));
                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500));
                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500));
                mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 29,20,0), "Ужин", 500));
       }


    public static List<UserMeal> getMealList() {
        return mealList;
    }



    public static List<UserMealWithExceed> getMealsWithExceed(List<UserMeal> mealList) {
        int caloriesPerDay = 500;
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));
        int count=0;
        List<UserMealWithExceed> mealExceeded = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalDateTime dateTime = meal.getDateTime();
                mealExceeded.add(new UserMealWithExceed(dateTime, meal.getDescription(), meal.getCalories(),
                        count, caloriesSumByDate.get(dateTime.toLocalDate()) > caloriesPerDay));
        count++;
        }
        return mealExceeded;

    }

    public static void mealsCreateAndProceed(LocalDateTime dateTime, String description, int calories){
        mealList.add(new UserMeal(dateTime,description,calories));
    }

    public static  void delete(int index){
        mealList.remove(index);
    }



    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay, int id) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(um->TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um->new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        id, caloriesSumByDate.get(um.getDateTime().toLocalDate())> caloriesPerDay))
                .collect(Collectors.toList());
    }








    public static List<UserMealWithExceed> getFilteredMealsWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay,int id) {

        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            caloriesSumPerDate.put(mealDate, caloriesSumPerDate.getOrDefault(mealDate, 0) + meal.getCalories());
        }

        List<UserMealWithExceed> mealExceeded = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                mealExceeded.add(new UserMealWithExceed(dateTime, meal.getDescription(), meal.getCalories(),
                        id, caloriesSumPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
        return mealExceeded;
    }

}
