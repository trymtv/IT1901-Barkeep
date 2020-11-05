package api;

import database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarkeepApp {

	public static void main(String[] args) {
		Database.setDbUrl("jdbc:h2:../core/src/main/resources/barkeep");
		SpringApplication.run(BarkeepApp.class, args);
	}

}
