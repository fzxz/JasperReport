package ua.sevenLevelLabs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author Tatiana Marchuk
 *         16.03.16 : 8:42
 */
@Configuration
@EnableJpaRepositories("ua.sevenLevelLabs.repository")
public class RepositoryConfig {
}
