package api;

import database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"database", "api"})
@EntityScan("barkeep")
@EnableJpaRepositories("database")
public class BarkeepApp {

	public static void main(String[] args) {
		Database.setDbUrl("jdbc:h2:../core/src/main/resources/barkeep");
		SpringApplication.run(BarkeepApp.class, args);
	}

}
