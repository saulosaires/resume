package io.com.resume.config;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Doc4JConfig {

    @Bean
    ObjectFactory getObjectFactory() {
        return Context.getWmlObjectFactory();
    }

}
