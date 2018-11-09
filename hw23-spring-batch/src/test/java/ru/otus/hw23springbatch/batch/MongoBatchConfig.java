package ru.otus.hw23springbatch.batch;

import com.mongodb.Mongo;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@EnableAutoConfiguration
public class MongoBatchConfig {

    @Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version("2.4.5")
                .bindIp("127.0.0.1")
                .port(12345)
                .build();
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();

    }

}
