package org.example.models;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Channel {
    private final List<User> subscribers;
    private final List<Post> posts;
    private String name;

    public Channel( List<User> subscribers,List<Post> posts,String name ) {
        this.subscribers = subscribers;
        this.posts =  posts;
        this.setName(name);
    }

    public void subscribe(User user) {
        if (subscribers.contains(user)) {
            throw new IllegalArgumentException("User is already subscribed.");
        }
        subscribers.add(user);
    }

    public List<Post> getPostsForUser(User user) {
        if (subscribers.contains(user)) {
            return new ArrayList<>(posts);
        }
        return Collections.emptyList();
    }

    public void unsubscribe(User user) {
        if (!subscribers.contains(user)) {
            throw new IllegalArgumentException("User is not subscribed.");
        }
        subscribers.remove(user);
    }

    public void postMessage(String message ,Channel channel) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }
        Post post = new Post(message,channel);
        posts.add(post);
        notifySubscribers(message);
    }

    private void notifySubscribers(String message) {
        for (User user : subscribers) {
            user.receiveMessage(message);
        }
    }

    public void addPosts(Post post) {
        if (posts.contains(post)) {
            throw new IllegalArgumentException("Post is already there.");
        }
        posts.add(post);
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public List<User> getSubscribers() {
        return new ArrayList<>(subscribers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Channel name cannot be null or empty.");
        }
        this.name = name;
    }
}
