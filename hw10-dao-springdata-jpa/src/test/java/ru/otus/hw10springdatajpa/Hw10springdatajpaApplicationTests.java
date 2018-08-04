package ru.otus.hw10springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.hw10springdatajpa.config.H2DataSourceConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2DataSourceConfig.class})
public class Hw10springdatajpaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
