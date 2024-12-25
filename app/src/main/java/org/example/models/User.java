package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final List<Channel> subscribedChannels;

    public User(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
        this.subscribedChannels = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Channel> getSubscribedChannels() {
        return new ArrayList<>(subscribedChannels);
    }

    public void subscribeToChannel(Channel channel) {
        if (!subscribedChannels.contains(channel)) {
            subscribedChannels.add(channel);
        }
    }

    public void unsubscribeFromChannel(Channel channel) {
        subscribedChannels.remove(channel);
    }

    public void receiveMessage(String message) {
        System.out.println(username + " received: " + message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
