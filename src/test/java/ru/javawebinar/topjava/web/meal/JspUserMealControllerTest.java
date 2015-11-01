package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by admin on 30.10.2015.
 */
@Transactional
public class JspUserMealControllerTest extends AbstractControllerTest{
    public static final String URL = "/meals";

    @Autowired
    private UserMealService userMealService;

    @Test
    @Transactional
    public void testMealList() throws Exception {
        List<UserMealWithExceed> umWithExceed = (UserMealsUtil.getWithExceeded(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), LoggedUser.getCaloriesPerDay()));
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", equalTo(umWithExceed)))
                .andExpect(model().attribute("mealList", contains(umWithExceed.toArray())))
                .andExpect(model().attribute("mealList", hasItems(umWithExceed.toArray())));

    }


    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testEditForUpdate() throws Exception {

    }

    @Test
    public void testEditForCreate() throws Exception {

    }

    @Test
    public void testUpdateOrCreate() throws Exception {

    }

    @Test
    public void testGetBetween() throws Exception {

    }
}