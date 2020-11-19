package api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"database", "api", "barkeep", "api.config"})
@EntityScan({"barkeep", "api.config"})
@EnableJpaRepositories("database")
public class BarkeepApp {

    public static void main(String[] args) {
        SpringApplication.run(BarkeepApp.class, args);
    }

}
