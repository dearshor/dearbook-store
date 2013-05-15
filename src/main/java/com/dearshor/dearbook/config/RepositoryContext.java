package com.dearshor.dearbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration @Import(Infrastructure.class)
@EnableJpaRepositories("com.dearshor.dearbook.repository")
public class RepositoryContext {
}
