// With asssistance of ChatGPT and Github Copilot


package es.apidemo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.context.ApplicationContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.apidemo.pojo.CategoryDTO;

@Component("auctionConsumer")
@Configuration
public class ApiConsumer implements IApiConsumer, ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(ApiConsumer.class);

	private static ApplicationContext context;
	private RestClient restClient;
	
	@Bean
	/**
	 * RestClient is Spring's modern HTTP client: it replaces RestTemplate and supports
	 * fluent, type-safe API calls.
	 */
	RestClient restClient() {
	      return RestClient.create();
	}	
	
	//API Server Host and Port NOT hard-coded: Defined in application.properties
	@Value("${spring.server.url}")
	private String serverURL;
	
	@Value("${api.server.port}")
	private int serverPort;

	@Autowired
	public void setRestClient(RestClient rest) {
		restClient = rest;
	 }
	
	public ApiConsumer () { }
	
	/**
	 * The Spring ApplicationContext keeps the registry of all beans created by the container.
	 * This is why we can resolve a bean by its name when we want to select a specific consumer.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }
	
	public static IApiConsumer getApiConsumer(String beanName) {
		// This is a very simple example of the kind of logic a Factory provides:
		// it selects the correct implementation by a name and returns it as the shared interface.
		return (IApiConsumer)context.getBean(beanName);
	}

	
	/* *
	 * Interface implementation methods
	 * */
	
	@Override
	public String getCategories() {
		// As a client simulation, this method performs the HTTP request to the REST API server.
		CategoryDTO[] categories = restClient.get()
			.uri(serverURL + ":" + serverPort + "/auctions/categories")
			.retrieve()
			.body(CategoryDTO[].class);
		
		log.info("categories - All categories info ...");
		  
		if (categories != null) {
			for (CategoryDTO c: categories) {
				log.info(c.toString());
			}
		}
		return "categories -  all categories on console (if not null) ";
		
		
	}

}