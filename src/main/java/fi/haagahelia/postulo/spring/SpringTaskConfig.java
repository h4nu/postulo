package fi.haagahelia.postulo.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({ "fi.haagahelia.postulo.task" })
public class SpringTaskConfig {

}
