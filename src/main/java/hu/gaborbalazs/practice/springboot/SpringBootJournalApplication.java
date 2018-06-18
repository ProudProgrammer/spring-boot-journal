package hu.gaborbalazs.practice.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringBootJournalApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	@Value("${myqueue}")
	String queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Scheduled(fixedDelay = 3000L)
	public void sendMessage() {
		jmsTemplate.convertAndSend(queue, "Spring Boot Rocks! scheduled");
	}

	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate) {
		return args -> {
			LOG.info("Sending> ...");
			jmsTemplate.convertAndSend(queue, "Spring Boot Rocks!");
		};
	}

	@JmsListener(destination = "${myqueue}")
	public void simplerConsumer(String message) {
		LOG.info("Simpler Consumer> " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}
}
