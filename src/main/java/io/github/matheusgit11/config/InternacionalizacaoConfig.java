package io.github.matheusgit11.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig { // classe responsavel por validaras mensagens vistas por exemplo nos postman
                                         //ligando o messages.properties a este arquivo e exibindo nas nossas entidades e nos validitys

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); //destino do arquivo responsavel pelas mensagens de erro
        messageSource.setDefaultEncoding("ISO-8859-1"); // padrao para reconhecer a pontuacao
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource()); // estamos setando o como validador o messageSource
        return bean;
    }
}
