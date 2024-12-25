package org.example.models;

public class Post {

    private String content;
    private Channel chanel;

    public Post(String content ,Channel channel) {
        this.content = content;
        this.chanel =channel;
    }

    public Channel getChanel() {
        return chanel;
    }

    public void setChanel(Channel chanel) {
        this.chanel = chanel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
