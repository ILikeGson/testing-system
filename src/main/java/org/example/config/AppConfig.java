package org.example.config;

import org.example.dao.QuestionDao;
import org.example.dao.QuestionDaoImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {
    private AppProps props;

    public AppConfig(AppProps props) {
        this.props = props;
    }

    @Bean
    public QuestionDao getQuestionDao() {
        return new QuestionDaoImpl(props.getQuestions());
    }

    @Bean
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}

