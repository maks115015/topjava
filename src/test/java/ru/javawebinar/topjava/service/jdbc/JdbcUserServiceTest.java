package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles({JDBC})
public class JdbcUserServiceTest extends AbstractUserServiceTest {
    @org.junit.Before
    public void setUp() throws Exception {
        service.evictCache();
    }
}