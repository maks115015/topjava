package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),1);
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),1);
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),1);
        save(new UserMeal(2,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),2);
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),1);
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),1);
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }else {
            if (userMeal.getUserId()!=userId) throw new NotFoundException("Нельзя отредактировать эту еду!");
        }

            return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(int id, int userId) {
        if(repository.get(id).getUserId()==userId)
            repository.remove(id);
        else throw new NotFoundException("Нельзя удалить эту еду!");
    }

    @Override
    public UserMeal get(int id, int userId) {
        if(repository.get(id).getUserId()!=userId)
            throw new NotFoundException("Это не ваша еда!");
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        List<UserMeal> mealsByUser=new ArrayList<>();
        for(Map.Entry<Integer,UserMeal> meal:repository.entrySet()){
            if(meal.getValue().getUserId()==userId) mealsByUser.add(meal.getValue());
        }
        return mealsByUser;
    }
}

