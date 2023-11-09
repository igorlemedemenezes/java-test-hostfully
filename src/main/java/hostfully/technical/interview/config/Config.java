package hostfully.technical.interview.config;

import hostfully.technical.interview.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private DBService dbService;

    @Bean
	public boolean instantiateDataBase() {
		dbService.instantiateTestDataBase();
		return true;
	}


}
