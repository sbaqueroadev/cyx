package co.com.sbaqueroadev.cyxtera.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

	private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

	@Autowired
	OwnProperties properties;

	protected String getDatabaseName() {
    	if(properties.isHeroku()){        
    		return "heroku_xxxx";
    	}else{
    		return "cyxtera";
    	}
    }

	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		logger.info("Setting mongoDB client");
		return new MongoTemplate((MongoClient) mongo(), getDatabaseName());
	}
}