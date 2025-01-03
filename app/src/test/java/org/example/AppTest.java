/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.example.models.Channel;
import org.example.models.User;
import org.junit.jupiter.api.BeforeEach;

class AppTest {
       private Channel channel;
    private User user1;
    @SuppressWarnings("unused")
    private User user2;

    @BeforeEach
    public void setUp() {
        channel = new Channel(new ArrayList<>(), new ArrayList<>(), "Test Channel");
        user1 = new User("Alice");
        user2 = new User("Bob");
    }

    @Test
    public void testChannelCreation() {
        assertEquals("Test Channel", channel.getName(), "Channel name should be correctly set.");
        assertTrue(channel.getSubscribers().isEmpty(), "Subscribers list should be empty on initialization.");
        assertTrue(channel.getPosts().isEmpty(), "Posts list should be empty on initialization.");
    }

    @Test
    public void testSubscribeUser() {
        channel.subscribe(user1);
        assertTrue(channel.getSubscribers().contains(user1), "User should be subscribed.");
    }

    @Test
    public void testSubscribeDuplicateUserThrowsException() {
        channel.subscribe(user1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> channel.subscribe(user1));
        assertEquals("User is already subscribed.", exception.getMessage());
    }

    @Test
    public void testUnsubscribeUser() {
        channel.subscribe(user1);
        channel.unsubscribe(user1);
        assertFalse(channel.getSubscribers().contains(user1), "User should be unsubscribed.");
    }

    @Test
    public void testUnsubscribeNonexistentUserThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> channel.unsubscribe(user1));
        assertEquals("User is not subscribed.", exception.getMessage());
    }

    @Test
    public void testPostMessage() {
        channel.subscribe(user1);
        channel.postMessage("Hello, World!", channel);

        assertEquals(1, channel.getPosts().size(), "There should be one post.");
        assertEquals("Hello, World!", channel.getPosts().get(0).getContent(), "The post content should match.");
    }

    @Test
    public void testPostEmptyMessageThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> channel.postMessage("", channel));
        assertEquals("Message cannot be empty.", exception.getMessage());
    }

    @Test
    public void testSetName() {
        channel.setName("Updated Channel");
        assertEquals("Updated Channel", channel.getName(), "Channel name should be updated.");
    }

    @Test
    public void testSetNameWithEmptyThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> channel.setName(""));
        assertEquals("Channel name cannot be null or empty.", exception.getMessage());
    }
}
