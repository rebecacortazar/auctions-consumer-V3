package es.apidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import es.apidemo.consumer.ApiConsumer;
import es.apidemo.consumer.IApiConsumer;

@SpringBootApplication
public class ApiConsumerDemo implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ApiConsumerDemo.class);

	@Autowired
    private ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiConsumerDemo.class, args);
	}
		
	public void run (String...args) throws Exception {		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		IApiConsumer consumer = ApiConsumer.getApiConsumer("ApiConsumer");
		
		log.info("Testing first method: " + consumer.getCategories());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		 
		 String asciiArtEnd = 
		            "  _____ _   _ _____   _____ _   _ ____  \n" +
		            " |_   _| | | | ____| | ____| \\ | |  _ \\ \n" +
		            "   | | | |_| |  _|   |  _| |  \\| | | | |\n" +
		            "   | | |  _  | |___  | |___| |\\  | |_| |\n" +
		            "   |_| |_| |_|_____| |_____|_| \\_|____/ \n";

		  System.out.println(asciiArtEnd);
		  context.close();
				
	}
}


