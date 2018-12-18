package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MockMealRepository implements MealRepository {

    private final static List<Meal> meals = Arrays.asList(
            new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    private final static Map<Integer, Meal> mockRepository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger(6);

    static {
        meals.forEach(m -> mockRepository.put(m.getId(), m));
    }

    @Override
    public Meal get(int id) {
        return mockRepository.get(id);
    }

    @Override
    public void create(Meal meal) {
        meal.setId(idGenerator.incrementAndGet());
        mockRepository.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        mockRepository.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        mockRepository.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mockRepository.values());
    }

}
