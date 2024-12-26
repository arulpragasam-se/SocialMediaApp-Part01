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
        createStartUI();
    }

    private void createStartUI() {
        JFrame frame = new JFrame("Social Media App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Social Media App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton channelButton = createStyledButton("Use As A Channel");
        JButton userButton = createStyledButton("Use As a User");

        channelButton.addActionListener(e -> createMainUI(true));
        userButton.addActionListener(e -> createMainUI(false));
        buttonPanel.add(channelButton);
        buttonPanel.add(userButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createMainUI(boolean isChannel) {
        JFrame frame = new JFrame("Social Media App");
        frame.setSize(600, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Social Media App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (isChannel) {
            JButton createChannelButton = createStyledButton("Create Channel");
            createChannelButton.addActionListener(e -> createChannelUI());
            buttonPanel.add(createChannelButton);

            JButton postMessageButton = createStyledButton("Post Message");
            postMessageButton.addActionListener(e -> postMessageUI());
            buttonPanel.add(postMessageButton);
        } else {
            JButton subscribeButton = createStyledButton("Subscribe to Channel");
            subscribeButton.addActionListener(e -> subscribeUI());
            buttonPanel.add(subscribeButton);

            JButton unsubscribeButton = createStyledButton("Unsubscribe to Channel");
            unsubscribeButton.addActionListener(e -> unsubscribeUI());
            buttonPanel.add(unsubscribeButton);

            JButton viewPostsButton = createStyledButton("View Posts");
            viewPostsButton.addActionListener(e -> viewPostsUI());
            buttonPanel.add(viewPostsButton);
            
            JButton listChannelsButton = createStyledButton("List Channels");
            listChannelsButton.addActionListener(e -> listChannelsUI());
            buttonPanel.add(listChannelsButton);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(60, 120, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 100, 160), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return button;
    }

    private void createChannelUI() {
        JFrame frame = new JFrame("Create Channel");
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel channelLabel = new JLabel("Channel Name:");
        channelLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField channelNameField = new JTextField();
        JButton createButton = createStyledButton("Create");

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

        inputPanel.add(channelLabel, BorderLayout.NORTH);
        inputPanel.add(channelNameField, BorderLayout.CENTER);
        inputPanel.add(createButton, BorderLayout.SOUTH);
        frame.add(inputPanel);
        frame.setVisible(true);
    }

    private void subscribeUI() {
        // Main Frame
        JFrame frame = new JFrame("Subscribe to Channel");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        usernameField.setPreferredSize(new Dimension(usernameField.getWidth(), 25));
        // Channel Name Field
        JTextField channelNameField = new JTextField();
        channelNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        channelNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Subscribe Button
        JButton subscribeButton = new JButton("Subscribe");
        subscribeButton.setFont(new Font("Arial", Font.BOLD, 16));
        subscribeButton.setForeground(Color.WHITE);
        subscribeButton.setBackground(new Color(65, 105, 225));
        subscribeButton.setFocusPainted(false);
        subscribeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        subscribeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Action Listener
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

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Channel Name:"));
        inputPanel.add(channelNameField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(subscribeButton);

        // Add Components to Frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display Frame
        frame.setVisible(true);
    }

    private void unsubscribeUI() {
        // Main Frame
        JFrame frame = new JFrame("Unsubscribe from a Channel");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        usernameField.setPreferredSize(new Dimension(usernameField.getWidth(), 25));
        // Channel Name Field
        JTextField channelNameField = new JTextField();
        channelNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        channelNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Subscribe Button
        JButton unsubscribeButton = new JButton("UnSubscribe");
        unsubscribeButton.setFont(new Font("Arial", Font.BOLD, 16));
        unsubscribeButton.setForeground(Color.WHITE);
        unsubscribeButton.setBackground(new Color(65, 105, 225));
        unsubscribeButton.setFocusPainted(false);
        unsubscribeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        unsubscribeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Action Listener
        unsubscribeButton.addActionListener(e -> {
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
                controller.unsubscribeUser(user);
                JOptionPane.showMessageDialog(frame, "Unsubscribed successfully!");
                frame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Channel Name:"));
        inputPanel.add(channelNameField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(unsubscribeButton);

        // Add Components to Frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display Frame
        frame.setVisible(true);
    }



    private void postMessageUI() {
        // Main Frame
        JFrame frame = new JFrame("Post Message");
        try {
            frame.setSize(500, 350);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(new Color(240, 248, 255));

            // Channel Name Field
            JTextField channelNameField = new JTextField();
            channelNameField.setFont(new Font("Arial", Font.PLAIN, 16));
            channelNameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));

            // Message Field
            JTextField messageField = new JTextField();
            messageField.setFont(new Font("Arial", Font.PLAIN, 16));
            messageField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));

            // Post Button
            JButton postButton = new JButton("Post Message");
            postButton.setFont(new Font("Arial", Font.BOLD, 16));
            postButton.setForeground(Color.WHITE);
            postButton.setBackground(new Color(65, 105, 225));
            postButton.setFocusPainted(false);
            postButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            postButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Action Listener
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

            // Input Panel
            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            inputPanel.setBackground(new Color(240, 248, 255));
            inputPanel.add(new JLabel("Channel Name:"));
            inputPanel.add(channelNameField);
            inputPanel.add(new JLabel("Message:"));
            inputPanel.add(messageField);

            // Button Panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(240, 248, 255));
            buttonPanel.add(postButton);

            // Add Components to Frame
            frame.add(inputPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Display Frame
            frame.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
            return;
        }

    }

    private void viewPostsUI() {
        // Main Frame
        JFrame frame = new JFrame("View Posts");
        try {
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(new Color(240, 248, 255));

            // Username Field
            JTextField usernameField = new JTextField();
            usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
            usernameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));

            // Channel Name Field
            JTextField channelNameField = new JTextField();
            channelNameField.setFont(new Font("Arial", Font.PLAIN, 16));
            channelNameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));

            // View Button
            JButton viewButton = new JButton("View");
            viewButton.setFont(new Font("Arial", Font.BOLD, 16));
            viewButton.setForeground(Color.WHITE);
            viewButton.setBackground(new Color(65, 105, 225));
            viewButton.setFocusPainted(false);
            viewButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Posts Area
            JTextArea postsArea = new JTextArea();
            postsArea.setEditable(false);
            postsArea.setFont(new Font("Arial", Font.PLAIN, 14));
            postsArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(211, 211, 211), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            postsArea.setLineWrap(true);
            postsArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(postsArea);
            scrollPane.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    "Posts",
                    0,
                    0,
                    new Font("Arial", Font.BOLD, 14),
                    new Color(65, 105, 225)));

            // Input Panel
            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            inputPanel.setBackground(new Color(240, 248, 255));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            inputPanel.add(new JLabel("Username:"));
            inputPanel.add(usernameField);
            inputPanel.add(new JLabel("Channel Name:"));
            inputPanel.add(channelNameField);
            inputPanel.add(new JLabel()); // Placeholder for alignment
            inputPanel.add(viewButton);

            // Action Listener
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
                if (!controller.getChannel().getSubscribers().contains(user)) {
                    JOptionPane.showMessageDialog(frame, "User not subscribed.");
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

            // Add Panels to Frame
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Display Frame
            frame.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
            return;
        }

    }

    private void listChannelsUI() {
        JFrame frame = new JFrame("List Channels");
        try {
            // main frame
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // main panel with a border layout
            JPanel mainPanel = new JPanel(new BorderLayout());

            // title label at the top
            JLabel titleLabel = new JLabel("Available Channels", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setOpaque(true);
            titleLabel.setBackground(new Color(0x4682B4)); // Steel Blue
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainPanel.add(titleLabel, BorderLayout.NORTH);

            // text area for displaying channels
            JTextArea channelsArea = new JTextArea();
            channelsArea.setEditable(false);
            channelsArea.setFont(new Font("Courier New", Font.PLAIN, 14));
            channelsArea.setBackground(new Color(0xF5F5F5)); // Light Gray
            channelsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Populate the text area
            if (channels.isEmpty()) {
                channelsArea.setText("No channels available.");
                channelsArea.setForeground(Color.GRAY);
            } else {
                for (String channelName : channels.keySet()) {
                    channelsArea.append(channelName + "\n");
                }
                channelsArea.setForeground(Color.BLACK);
            }

            // text area inside a scroll pane
            JScrollPane scrollPane = new JScrollPane(channelsArea);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Channels"));
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // close button
            JButton closeButton = new JButton("Close");
            closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
            closeButton.addActionListener(e -> frame.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add main panel to the frame
            frame.add(mainPanel);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(frame, ex.getMessage());
            return;

        }
    }

}
