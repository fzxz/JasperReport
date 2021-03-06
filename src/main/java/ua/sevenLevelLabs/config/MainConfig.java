package ua.sevenLevelLabs.config;

/**
 * @author Tatiana Marchuk
 * 16.03.16 : 8:41
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * Configuration of the business, persistence and security layers.
 */
@Configuration
@Import(value = {
        DataSourceConfig.class,
        InfrastructureConfig.class,
        RepositoryConfig.class
})
public class MainConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MainConfig.class);

    @Autowired
    private Environment env;

    /**
     * Application custom initialization code.
     * <p>
     * Spring profiles can be configured with a system property
     * -Dspring.profiles.active=javaee
     * <p>
     */
    @PostConstruct
    public void initApp() {
        LOG.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            LOG.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                LOG.info("Detected Spring profile: {}", profile);
            }
        }
    }
}