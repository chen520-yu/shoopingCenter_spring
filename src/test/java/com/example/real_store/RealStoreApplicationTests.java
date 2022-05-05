package com.example.real_store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class RealStoreApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
    @Test
    void contextLoads() {
    }

}
