package br.com.generator.genpdfcsv.configuration;

import br.com.generator.genpdfcsv.filter.CustomFilter;
import br.com.generator.genpdfcsv.filter.ResponseFilter;
import br.com.generator.genpdfcsv.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
@ComponentScan({"br.com.generator.genpdfcsv.configuration", "br.com.generator.genpdfcsv.resource", "br.com.generator.genpdfcsv.model"})
@EnableAutoConfiguration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }

//    @Bean
//    public FilterRegistrationBean customFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(createCustomFilter());
//        registration.setOrder(1);
//        return registration;
//    }

    @Bean
    public Filter createCustomFilter() {
        return new CustomFilter();
    }

//    @Bean
//    public Filter createResponseFilter() {
//        return new ResponseFilter();
//    }

}
