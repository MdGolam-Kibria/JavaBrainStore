package com.CrackCode.designPattern.behavioralDesignPatterns.ObserverPattern;

import java.util.Objects;

public class ProthomAloSubscriber implements Channel {
    private String news;

    @Override
    public void update(String message) {
        this.news = message;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProthomAloSubscriber that = (ProthomAloSubscriber) o;
        return Objects.equals(news, that.news);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(news);
    }
}
