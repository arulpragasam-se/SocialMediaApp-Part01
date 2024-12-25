package org.example.controllers;

import org.example.models.Channel;
import org.example.models.Post;
import org.example.models.User;
import java.util.stream.Collectors;

import java.util.List;

public class ChannelController {
    private final Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public ChannelController(Channel channel) {
        this.channel = channel;
    }

    public void addSubscriber(String username) {
        User user = new User(username);
        channel.subscribe(user);
    }

    public void removeSubscriber(String username) {
        User user = new User(username);
        channel.unsubscribe(user);
    }

    public void subscribeUser(User user) {
        channel.subscribe(user);
    }

    public void unsubscribeUser(User user) {
        channel.unsubscribe(user);
    }


    public void addPost(Post post) {
        channel.addPosts(post);
    }

    public void postMessage(String message,Channel chanel) {
        channel.postMessage(message,chanel);
    }

    public String getChannelName() {
        return channel.getName();
    }

    public void setChannelName(String name) {
        channel.setName(name);
    }

    public List<Post> getPosts() {
        return channel.getPosts();
    }

    public List<String> getSubscribers() {
        return channel.getSubscribers().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
