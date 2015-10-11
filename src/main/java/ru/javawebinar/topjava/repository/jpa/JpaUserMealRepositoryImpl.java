package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
            if (userMeal.isNew()) {
                User ref = em.getReference(User.class, userId);
                userMeal.setUser(ref);
                em.persist(userMeal);
                return userMeal;
            } else {
                return userMeal.getUser().getId()==userId?em.merge(userMeal):null;
            }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(UserMeal.DELETE).setParameter("id", id).setParameter("user_id", userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal ref=em.find(UserMeal.class, id);
        return ref.getUser().getId()==userId?ref:null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        User ref = em.getReference(User.class, userId);
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GETBETWEEN, UserMeal.class).setParameter("user_id", userId)
                .setParameter(1,startDate)
                .setParameter(2,endDate)
                .getResultList();
    }
}