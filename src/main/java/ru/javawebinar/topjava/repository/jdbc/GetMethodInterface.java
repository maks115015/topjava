package ru.javawebinar.topjava.repository.jdbc;

import ru.javawebinar.topjava.model.Role;

import java.util.Set;

/**
 * Created by admin on 20.10.2015.
 */
public interface GetMethodInterface {
    public Set<Role> getUserRoles(int id);
}
