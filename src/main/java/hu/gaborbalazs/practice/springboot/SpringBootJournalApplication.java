package hu.gaborbalazs.practice.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import hu.gaborbalazs.practice.springboot.domain.Journal;
import hu.gaborbalazs.practice.springboot.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	private static final String APPLICATION_PROPERTIES = "application.properties";
	
	private static final String FORWARD_MARK = "> ";

	@Bean
	public InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016"));
			repo.save(
					new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01/02/2016"));
			repo.save(new Journal("Spring Boot Reading", "Read more about Spring Boot", "02/01/2016"));
			repo.save(new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016"));
		};
	}

	@Bean
	@DependsOn(APPLICATION_PROPERTIES)
	public CommandLineRunner values(@Qualifier(APPLICATION_PROPERTIES) List<String> props) {
		return args -> {
			props.forEach(prop -> LOGGER.info(FORWARD_MARK + prop));
		};
	}

	@Bean(APPLICATION_PROPERTIES)
	public List<String> getProperties() throws IOException {
		List<String> props = new ArrayList<>();
		BufferedReader br;
		String line;
		br = new BufferedReader(new InputStreamReader(new ClassPathResource(APPLICATION_PROPERTIES).getInputStream()));
		while ((line = br.readLine()) != null) {
			if (StringUtils.isNotBlank(line) && !StringUtils.startsWith(line, "#")) {
				props.add(line);
			}
		}
		return props;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);
		app.run(args);
	}
}
