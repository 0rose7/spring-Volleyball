package com.spring.volleyball.config;


//import com.spring.volleyball.service.NumberService;  -- example
//import com.spring.volleyball.service.NumberServiceImpl; -- example
import com.spring.volleyball.service.UserService;
import com.spring.volleyball.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    @Qualifier("UserService")
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    @Qualifier("NumberService")
    public NumberService getNumberService() {
        return new NumberServiceImpl();
    }
}