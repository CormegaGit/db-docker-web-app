package com.example.db_docker_web_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DbDockerWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbDockerWebAppApplication.class, args);
	}
}

@Component
class FileInitializer implements CommandLineRunner {

	private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(FileInitializer.class.getName());

	@Value("${data.file.path}")
	String dataFilePath;

	@Override
	public void run(String... args) throws Exception {
		File file = new File(dataFilePath);
		if (!file.exists()) {
			createJsonFile(file);
			log.info("{\"Demo App\": \"File '" + dataFilePath + "' does NOT exit, creating new file...\"}");
		} else {
			log.info("{\"Demo App\": \"File '" + dataFilePath + "' exist in current path.\"}");
		}
	}

	private void createJsonFile(File file) throws IOException {
		List<Person> people = Arrays.asList(
				new Person("John", "Doe", "USA"),
				new Person("Jane", "Smith", "Canada"),
				new Person("Peter", "Brown", "UK")
		);

		try (FileWriter writer = new FileWriter(file)) {
			writer.write("[");
			for (int i = 0; i < people.size(); i++) {
				Person person = people.get(i);
				writer.write("{\"firstName\": \"" + person.firstName + "\", \"lastName\": \"" + person.lastName + "\", \"country\": \"" + person.country + "\"}");
				if (i < people.size() - 1) {
					writer.write(",");
				}
				writer.write("\n");
			}
			writer.write("]");
		}
	}
}
