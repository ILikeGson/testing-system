package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;


@ConfigurationProperties(prefix = "application")
public class AppProps {
    private Locale locale;
    private String questions;
    private int scoresToPass;


    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getScoresToPass() {
        return scoresToPass;
    }

    public void setScoresToPass(int scoresToPath) {
        this.scoresToPass = scoresToPath;
    }
}
