package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(LoggedUser.getUserId());
    }

    public void delete() {
        super.delete(LoggedUser.getUserId());
    }

    public void update(User user) {
        super.update(user, LoggedUser.getUserId());
    }
}