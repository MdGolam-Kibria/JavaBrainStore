package com.CrackCode.designPattern.behavioralDesignPatterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class NewsAgencyObserver {
    private List<Channel> channelList = new ArrayList<>();


    public void addObserver(Channel channel) {
        channelList.add(channel);
    }

    public void removeObserver(Channel channel) {
        channelList.remove(channel);
    }

    public void setNews(String news) {
        for (Channel channel : channelList) {
            channel.update(news);
        }
    }

}
