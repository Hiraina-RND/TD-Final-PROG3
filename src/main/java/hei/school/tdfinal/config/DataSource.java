package hei.school.tdfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {
    @Bean
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("", "", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

