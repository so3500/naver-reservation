package com.nts.pjt3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.nts.pjt3.dao", "com.nts.pjt3.service"})
@Import({DBConfig.class})
public class ApplicationConfig {}