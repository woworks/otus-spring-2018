package ru.otus.hw21springacl.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
//@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws IOException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(54321)
                .start();
        return pg.getPostgresDatabase();
    }
}
