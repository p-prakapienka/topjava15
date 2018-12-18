package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MockMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository mealRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        mealRepository = new MockMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");
        if ("delete".equals(action)) {
            doPost(req, resp);
            return;
        }
        final String id = req.getParameter("id");
        final Meal meal =  id != null ? mealRepository.get(Integer.parseInt(id)) : new Meal();
        req.setAttribute("meal", meal);
        req.setAttribute("meals", MealsUtil.getFilteredWithExceed(mealRepository.getAll()));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String action = req.getParameter("action");
        switch (action) {
            case "create":
                mealRepository.create(extractMealFromRequest(req));
                break;
            case "update":
                mealRepository.update(extractMealFromRequest(req));
                break;
            case "delete":
                mealRepository.delete(Integer.parseInt(req.getParameter("id")));
                break;
        }
        resp.sendRedirect("meals");
    }

    private Meal extractMealFromRequest(HttpServletRequest request) {
        return new Meal(
                Integer.parseInt(request.getParameter("id")),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories"))
        );
    }

}
