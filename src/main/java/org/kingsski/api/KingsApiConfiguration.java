package org.kingsski.api;

import org.kingsski.api.dao.team.RepositoryTeamDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KingsApiConfiguration {

    @Bean("repositoryTeamService")
    public RepositoryTeamDao repositoryTeamService() {
        return new RepositoryTeamDao();
    }
}
