package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController {
    static final String REST_URL="/rest/meals";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {

        return super.getAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal userMeal) {
        super.update(userMeal);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMeal> createMeal(@RequestBody UserMeal userMeal) {
        UserMeal created = super.create(userMeal);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getFiltered(@RequestParam(value = "startDate", required = false) String startDate, @RequestParam(value = "startTime", required = false) String startTime,
                                                @RequestParam(value = "endDate", required = false) String endDate,@RequestParam(value = "endTime", required = false) String endTime){
        LocalDate startDate1 = TimeUtil.parseLocalDate(startDate, TimeUtil.MIN_DATE);
        LocalDate endDate1 = TimeUtil.parseLocalDate(endDate, TimeUtil.MAX_DATE);
        LocalTime startTime1 = TimeUtil.parseLocalTime(startTime, LocalTime.MIN);
        LocalTime endTime1 = TimeUtil.parseLocalTime(endTime, LocalTime.MAX);
        return super.getBetween(startDate1,startTime1,endDate1,endTime1);
    }

}