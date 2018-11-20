package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000).forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();

        final Map<LocalDate, Integer> caloriesPerDate = new HashMap<>();
        mealList.forEach(m -> {
            caloriesPerDate.computeIfPresent(m.getDateTime().toLocalDate(), (k, v) -> v + m.getCalories());
            caloriesPerDate.putIfAbsent(m.getDateTime().toLocalDate(), m.getCalories());
        });
        mealList.forEach(m -> {
            if (TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceedList.add(new UserMealWithExceed(
                        m.getDateTime(),
                        m.getDescription(),
                        m.getCalories(),
                        caloriesPerDate.get(m.getDateTime().toLocalDate()) > caloriesPerDay
                ));
            }
        });
        return userMealWithExceedList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDate = mealList
                .stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                                               Collectors.summingInt(UserMeal::getCalories)));

        return mealList
                .stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(),
                                                  um.getDescription(),
                                                  um.getCalories(),
                                                  caloriesPerDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
