package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.delegate.RepositoryDaoDelegateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("repository")
public class RepositoryDaoDelegateConfig {

    @Bean("delegate")
    public DaoDelegateFactory daoDelegateConfig() {
        return new RepositoryDaoDelegateFactory();
    }
}
