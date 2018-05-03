package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.delegate.StubDaoDelegateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stub")
public class StubDaoDelegateConfig {

    @Bean("delegate")
    public DaoDelegateFactory daoDelegateConfig() {
        return new StubDaoDelegateFactory();
    }
}
