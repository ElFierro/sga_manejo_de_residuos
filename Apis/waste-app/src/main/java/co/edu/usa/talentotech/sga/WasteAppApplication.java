package co.edu.usa.talentotech.sga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class WasteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WasteAppApplication.class, args);
	}

}
