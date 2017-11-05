package br.com.generator.genpdfcsv.configuration;

import br.com.generator.genpdfcsv.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Filter createCustomFilter() {
        return new CustomFilter();
    }

}
