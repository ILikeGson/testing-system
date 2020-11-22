package org.example.service;

import org.example.config.AppProps;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


@Service
public class MessageSourceLocalizationService implements LocalizationService{
    private MessageSource messageSource;
    private AppProps props;

    public MessageSourceLocalizationService(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
    }

    public String getMessage(String str, Object ...objects) {
        return messageSource.getMessage(str, objects, props.getLocale());
    }
}
