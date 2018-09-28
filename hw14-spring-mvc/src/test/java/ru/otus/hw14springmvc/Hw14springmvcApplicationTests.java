package ru.otus.hw14springmvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class Hw14springmvcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
