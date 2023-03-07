package in.neuw.self;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    @Bean
    public RestTemplate restTemplate(@Value("${downstream.basepath}") String rootUri) {
        return new RestTemplateBuilder().rootUri(rootUri).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}
