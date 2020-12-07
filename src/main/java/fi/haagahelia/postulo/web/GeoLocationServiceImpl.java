package fi.haagahelia.postulo.web;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;

@Service("geolocationservice")
public class GeoLocationServiceImpl implements GeoLocationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeoLocationServiceImpl.class);

    private static DatabaseReader reader = null;
    private ResourceLoader resourceLoader;

    /* not yet implemented
    @Inject
    public GeoLocationServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    */

    @PostConstruct
    public void init() {
        try {
            LOGGER.info("GeoLocationServiceImpl: Trying to load GeoLite2-Country database...");

            Resource resource = resourceLoader.getResource("classpath:GeoLite2-Country.mmdb");
            InputStream dbAsStream = resource.getInputStream(); // <-- this is the difference

            // Initialize the reader
            reader = new DatabaseReader
                        .Builder(dbAsStream)
                        // not yet implemented
                        // .fileMode(Reader.FileMode.MEMORY)
                        .build();

            LOGGER.info("GeoLocationServiceImpl: Database was loaded successfully.");

        } catch (IOException | NullPointerException e) {
            LOGGER.error("Database reader cound not be initialized. ", e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Failed to close the reader.");
            }
        }
    }

}
