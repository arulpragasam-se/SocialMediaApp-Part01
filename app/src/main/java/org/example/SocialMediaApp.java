package org.example;

import org.example.controllers.ChannelController;
import org.example.models.Channel;
import org.example.models.Post;
import org.example.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SocialMediaApp {
    private static final Map<String, ChannelController> channels = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Social Media App!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new channel");
            System.out.println("2. Subscribe to a channel");
            System.out.println("3. Post a message to a channel");
            System.out.println("4. View posts from a channel");
            System.out.println("5. List all channels");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> createChannel(scanner);
                case 2 -> subscribeToChannel(scanner);
                case 3 -> postMessage(scanner);
                case 4 -> viewPosts(scanner);
                case 5 -> listChannels();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createChannel(Scanner scanner) {
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();

        if (channels.containsKey(channelName)) {
            System.out.println("Channel already exists!");
            return;
        }

        Channel channel = new Channel(new ArrayList<>(), new ArrayList<>(), channelName);
        ChannelController controller = new ChannelController(channel);
        channels.put(channelName, controller);
        System.out.println("Channel created successfully!");
    }

    private static void subscribeToChannel(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter channel name to subscribe: ");
        String channelName = scanner.nextLine();

        ChannelController controller = channels.get(channelName);
        if (controller == null) {
            System.out.println("Channel not found!");
            return;
        }

        User user = users.computeIfAbsent(username, User::new);
        try {
            controller.subscribeUser(user);
            user.subscribeToChannel(controller.getChannel());
            System.out.println("Subscribed to channel successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void postMessage(Scanner scanner) {
        System.out.print("Enter channel name: ");
        String channelName = scanner.nextLine();
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();

        ChannelController controller = channels.get(channelName);
        if (controller == null) {
            System.out.println("Channel not found!");
            return;
        }

        try {
            controller.postMessage(message, controller.getChannel());
            System.out.println("Message posted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewPosts(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter channel name to view posts: ");
        String channelName = scanner.nextLine();

        ChannelController controller = channels.get(channelName);
        if (controller == null) {
            System.out.println("Channel not found!");
            return;
        }

        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found. Please subscribe to a channel first.");
            return;
        }

        List<Post> posts = controller.getChannel().getPostsForUser(user);
        if (posts.isEmpty()) {
            System.out.println("No posts available or you are not subscribed to this channel.");
        } else {
            System.out.println("Posts from " + channelName + ":");
            posts.forEach(post -> System.out.println("- " + post.getContent()));
        }
    }

    private static void listChannels() {
        if (channels.isEmpty()) {
            System.out.println("No channels available.");
        } else {
            System.out.println("Available channels:");
            channels.keySet().forEach(System.out::println);
        }
    }
}
