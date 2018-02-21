package org.kingsski.api;

import org.kingsski.api.service.repository.RepositoryTeamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KingsApiConfiguration {

    @Bean("repositoryTeamService")
    public RepositoryTeamService repositoryTeamService() {
        return new RepositoryTeamService();
    }
}
