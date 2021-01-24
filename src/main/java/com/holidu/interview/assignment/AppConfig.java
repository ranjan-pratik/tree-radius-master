package com.holidu.interview.assignment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.holidu.interview.assignment.model.CachedTreeData;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean(initMethod="init")
    public CachedTreeData cachedTreeData(){
        return new CachedTreeData();
    }   

}
