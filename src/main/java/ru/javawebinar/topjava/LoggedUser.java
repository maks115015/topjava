package ru.javawebinar.topjava;

import static java.util.Objects.requireNonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.UserUtil;
/**
 * GKislin
 * 06.03.2015.
 * <p/>
 * Mock implementation
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private final UserTo userTo;
    public static int id = 100000;

    public LoggedUser(User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.getRoles());

        this.userTo = UserUtil.asTo(user);
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public static int id() {
        return get().userTo.getId();
    }


    public static int getCaloriesPerDay() {
        return get().userTo.getCaloriesPerDay();
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
