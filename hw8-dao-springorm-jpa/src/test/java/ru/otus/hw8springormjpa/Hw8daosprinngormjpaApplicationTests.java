package ru.otus.hw8springormjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.hw8springormjpa.config.H2DataSourceConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {H2DataSourceConfig.class})
public class Hw8daosprinngormjpaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
