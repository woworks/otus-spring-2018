package ru.otus.hw6daospringjdbc;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
public class Hw6daospringjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw6daospringjdbcApplication.class, args);
    }

    @Bean
    public DataSource dataSource() throws IOException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder()
                .start();
        return pg.getPostgresDatabase();
    }
}
