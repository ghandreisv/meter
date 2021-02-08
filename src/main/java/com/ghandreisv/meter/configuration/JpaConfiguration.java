package com.ghandreisv.meter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.ghandreisv.meter.repository")
@EnableTransactionManagement
public class JpaConfiguration {
}
