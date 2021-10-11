package hu.elosztott.iit.me.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MainConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
