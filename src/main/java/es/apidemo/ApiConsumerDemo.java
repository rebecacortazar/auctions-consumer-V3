// Assisted by ChatGPT and GitHub Copilot for educational/demo purposes.
// This example illustrates Spring Boot, bean-name lookup, interface-based design,
// RestClient usage, and the factory-like pattern behind selecting a consumer.

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

	/**
	 * This context is only injected to allow us to close the Spring application context at the end.
	 */
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
		
		/**
		 * This is very similar to a simple Factory: based on the chosen bean name,
		 * we obtain the concrete consumer object that will make the REST request.
		 * The complexity is hidden behind the IApiConsumer interface, so the caller
		 * only depends on the common contract and not on the concrete implementation.
		 */
		IApiConsumer consumer = ApiConsumer.getApiConsumer("auctionConsumer");
		
		/**
		 * This call represents the client request to the external REST API server.
		 * The concrete consumer object hides the protocol details; we only invoke the shared interface.
		 */
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


