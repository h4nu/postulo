package fi.haagahelia.postulo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.haagahelia.postulo.security.ActiveUserStore;

@Configuration
public class AppConfig {
	
	@Bean
    public ActiveUserStore activeUserStore() {
        return new ActiveUserStore();
    }

}
