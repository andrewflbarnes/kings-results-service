package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.delegate.DummyDaoDelegateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dummy")
public class DummyDaoDelegateConfig {

    @Bean("delegate")
    public DaoDelegateFactory daoDelegateConfig() {
        return new DummyDaoDelegateFactory();
    }
}
