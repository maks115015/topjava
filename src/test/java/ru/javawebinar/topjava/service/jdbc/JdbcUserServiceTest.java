package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.jdbc.GetMethodInterface;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles({JDBC})
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    private GetMethodInterface getMethodInterface;

    @Autowired
    private UserRepository userRepository;

    @org.junit.Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testGetRoles() throws Exception {
        if (!ADMIN.getRoles().equals(getMethodInterface.getUserRoles(ADMIN_ID))) throw new NotFoundException("неправильные роли");

    }


}