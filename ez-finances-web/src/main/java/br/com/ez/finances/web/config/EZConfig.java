package br.com.ez.finances.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ez.finances.web.interceptor.EZExceptionHandler;

/**
 * Configuration for the EZ application.
 */
@Configuration
public class EZConfig implements WebMvcConfigurer {

    private EZExceptionHandler ezExceptionHandler;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("locale/messages");
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

}
