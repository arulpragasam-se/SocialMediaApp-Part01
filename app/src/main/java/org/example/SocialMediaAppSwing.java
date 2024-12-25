package org.example;

import org.example.controllers.ChannelController;
import org.example.models.Channel;
import org.example.models.Post;
import org.example.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialMediaAppSwing {
    private final Map<String, ChannelController> channels = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SocialMediaAppSwing::new);
    }

    public SocialMediaAppSwing() {
        createMainUI();
    }

    private void createMainUI() {
        JFrame frame = new JFrame("Social Media App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton createChannelButton = new JButton("Create Channel");
        JButton subscribeButton = new JButton("Subscribe to Channel");
        JButton postMessageButton = new JButton("Post Message");
        JButton viewPostsButton = new JButton("View Posts");
        JButton listChannelsButton = new JButton("List Channels");

        createChannelButton.addActionListener(e -> createChannelUI());
        subscribeButton.addActionListener(e -> subscribeUI());
        postMessageButton.addActionListener(e -> postMessageUI());
        viewPostsButton.addActionListener(e -> viewPostsUI());
        listChannelsButton.addActionListener(e -> listChannelsUI());

        mainPanel.add(createChannelButton);
        mainPanel.add(subscribeButton);
        mainPanel.add(postMessageButton);
        mainPanel.add(viewPostsButton);
        mainPanel.add(listChannelsButton);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createChannelUI() {
        JFrame frame = new JFrame("Create Channel");
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JTextField channelNameField = new JTextField();
        JButton createButton = new JButton("Create");

        createButton.addActionListener(e -> {
            String channelName = channelNameField.getText().trim();
            if (channelName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Channel name cannot be empty.");
                return;
            }
            if (channels.containsKey(channelName)) {
                JOptionPane.showMessageDialog(frame, "Channel already exists.");
                return;
            }
            Channel channel = new Channel(new ArrayList<>(), new ArrayList<>(), channelName);
            channels.put(channelName, new ChannelController(channel));
            JOptionPane.showMessageDialog(frame, "Channel created successfully!");
            frame.dispose();
        });

        frame.add(new JLabel("Channel Name:"), BorderLayout.NORTH);
        frame.add(channelNameField, BorderLayout.CENTER);
        frame.add(createButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void subscribeUI() {
        JFrame frame = new JFrame("Subscribe to Channel");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 1));

        JTextField usernameField = new JTextField();
        JTextField channelNameField = new JTextField();
        JButton subscribeButton = new JButton("Subscribe");

        subscribeButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String channelName = channelNameField.getText().trim();
            if (username.isEmpty() || channelName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both fields are required.");
                return;
            }
            ChannelController controller = channels.get(channelName);
            if (controller == null) {
                JOptionPane.showMessageDialog(frame, "Channel not found.");
                return;
            }
            User user = users.computeIfAbsent(username, User::new);
            try {
                controller.subscribeUser(user);
                JOptionPane.showMessageDialog(frame, "Subscribed successfully!");
                frame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Channel Name:"));
        frame.add(channelNameField);
        frame.add(subscribeButton);

        frame.setVisible(true);
    }

    private void postMessageUI() {
        JFrame frame = new JFrame("Post Message");
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 1));

        JTextField channelNameField = new JTextField();
        JTextField messageField = new JTextField();
        JButton postButton = new JButton("Post");

        postButton.addActionListener(e -> {
            String channelName = channelNameField.getText().trim();
            String message = messageField.getText().trim();
            if (channelName.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both fields are required.");
                return;
            }
            ChannelController controller = channels.get(channelName);
            if (controller == null) {
                JOptionPane.showMessageDialog(frame, "Channel not found.");
                return;
            }
            try {
                controller.postMessage(message, controller.getChannel());
                JOptionPane.showMessageDialog(frame, "Message posted successfully!");
                frame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        frame.add(new JLabel("Channel Name:"));
        frame.add(channelNameField);
        frame.add(new JLabel("Message:"));
        frame.add(messageField);
        frame.add(postButton);

        frame.setVisible(true);
    }

    private void viewPostsUI() {
        JFrame frame = new JFrame("View Posts");
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        JTextField usernameField = new JTextField();
        JTextField channelNameField = new JTextField();
        JButton viewButton = new JButton("View");
        JTextArea postsArea = new JTextArea();
        postsArea.setEditable(false);

        viewButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String channelName = channelNameField.getText().trim();
            if (username.isEmpty() || channelName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both fields are required.");
                return;
            }
            ChannelController controller = channels.get(channelName);
            if (controller == null) {
                JOptionPane.showMessageDialog(frame, "Channel not found.");
                return;
            }
            User user = users.get(username);
            if (user == null) {
                JOptionPane.showMessageDialog(frame, "User not found or not subscribed.");
                return;
            }
            List<Post> posts = controller.getChannel().getPostsForUser(user);
            postsArea.setText("");
            if (posts.isEmpty()) {
                postsArea.setText("No posts available.");
            } else {
                for (Post post : posts) {
                    postsArea.append(post.getContent() + "\n");
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Channel Name:"));
        inputPanel.add(channelNameField);
        inputPanel.add(viewButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(postsArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void listChannelsUI() {
        JFrame frame = new JFrame("List Channels");
        frame.setSize(300, 200);

        JTextArea channelsArea = new JTextArea();
        channelsArea.setEditable(false);

        if (channels.isEmpty()) {
            channelsArea.setText("No channels available.");
        } else {
            for (String channelName : channels.keySet()) {
                channelsArea.append(channelName + "\n");
            }
        }

        frame.add(new JScrollPane(channelsArea));
        frame.setVisible(true);
    }
}
