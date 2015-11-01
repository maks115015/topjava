package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by admin on 29.10.2015.
 */
public class UserMealRestControllerTest extends AbstractControllerTest{

    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Autowired
    private UserMealService userMealService;

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> userMeal= Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1);
        TestUtil.print(mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(userMeal, LoggedUser.getCaloriesPerDay())));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));


    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
                MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5,MEAL4,MEAL3,MEAL2),userMealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated=new UserMeal(MEAL1);
        updated.setDescription("Обновленный завтрак");
        updated.setCalories(200);
        mockMvc.perform(put(REST_URL+MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(getUpdated(),userMealService.get(MEAL1_ID,LoggedUser.id()));
    }

    @Test
    public void testCreateMeal() throws Exception {
        TestUserMeal testUserMeal=new TestUserMeal(LocalDateTime.parse("2015-10-30T12:52"),"created meal",600);
        ResultActions resultActions=mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(testUserMeal.asUser()))).andExpect(status().isCreated());

        UserMeal returned=MATCHER.fromJsonAction(resultActions);
        testUserMeal.setId(returned.getId());

        MATCHER.assertEquals(testUserMeal, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(testUserMeal,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),userMealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testGetFiltered() throws Exception {
        List<UserMeal> userMeal= Arrays.asList(MEAL6,MEAL5,MEAL4);
        mockMvc.perform(post(REST_URL + "filter")
                .param("startDate","2015-05-31")
                .param("endDate","2015-06-30")
                /*.param("startTime","00:00:00")
                .param("endTime","23:59:59")*/
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(userMeal, LoggedUser.getCaloriesPerDay())));
    }

}