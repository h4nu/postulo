package fi.haagahelia.postulo.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "fi.haagahelia.postulo.web" })
public class ServiceConfig {
}
