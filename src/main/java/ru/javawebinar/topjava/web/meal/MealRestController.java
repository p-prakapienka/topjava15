package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void update(Meal meal) {
        service.update(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public List<MealWithExceed> getAll() {
        return service.getAll(SecurityUtil.authUserId());
    }

}