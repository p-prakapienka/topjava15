package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    Meal get(int id);

    void create(Meal meal);

    void update(Meal meal);

    void delete(int id);

    List<Meal> getAll();
}
