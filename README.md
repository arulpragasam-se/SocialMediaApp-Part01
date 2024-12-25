# Social Media App

This is a Java-based social media application with a command-line interface and a GUI using Swing. The application allows users to create channels, subscribe to them, post messages, and view posts. It is structured using an MVC (Model-View-Controller) pattern and built using Gradle.

## Features

- Create channels.
- Subscribe to channels.
- Post messages to channels.
- View posts from subscribed channels.
- Command-line and GUI interfaces.

---

## Prerequisites

To run this application on your local machine, you need:

- **Java Development Kit (JDK)**: Version 17 or later (Java 21 is recommended).
- **Gradle**: Installed or use the Gradle wrapper included in the project.
- **Git**: Installed for cloning the repository.

---

## Installation

1. **Clone the Repository**:

   Open a terminal and execute the following command:

   ```bash
   git clone https://github.com/arulpragasam-se/SocialMediaApp-Part01.git
   cd socialmediaapp-part01
   ```

2. **Build the Project**:

   Navigate to the project directory and run:

   ```bash
   ./gradlew build
   ```

   On Windows, use:

   ```bash
   gradlew.bat build
   ```

3. **Run the Application**:

   To launch the application, execute:

   ```bash
   ./gradlew run
   ```

   On Windows, use:

   ```bash
   gradlew.bat run
   ```

   Alternatively, you can run the `App` class directly from your IDE (e.g., IntelliJ IDEA or Eclipse).

---

## Using the Application

### Command-Line Interface

1. After running the application, you will be presented with a menu to perform operations such as creating channels, subscribing, posting messages, and viewing posts.
2. Follow the on-screen instructions to navigate through the application.

### Graphical User Interface (GUI)

1. Launch the application to open a Swing-based user interface.
2. Use the buttons to create channels, subscribe, post messages, view posts, or list available channels.

---

## Project Structure

- **`src/main/java/org/example/models`**: Contains model classes like `Channel`, `Post`, and `User`.
- **`src/main/java/org/example/controllers`**: Contains the `ChannelController` class to manage the application's business logic.
- **`src/main/java/org/example`**: Contains the `SocialMediaApp` (CLI) and `SocialMediaAppSwing` (GUI) entry points.
- **`src/test/java/org/example`**: Contains unit tests to validate application behavior.
  - **Tests Overview**:
    - Validates core functionalities of `Channel`, `User`, and `Post` models.
    - Ensures exception handling (e.g., subscribing duplicate users or empty messages).
    - Covers both success and failure scenarios for operations like subscribing, posting, and renaming channels.
    - Example test case: `testSubscribeDuplicateUserThrowsException` ensures a user cannot subscribe twice to the same channel.

- **`build.gradle`**: Gradle build configuration file.
- **`.gitignore`**: Specifies files and directories to ignore in Git.

---



## Support

If you encounter any issues, feel free to open an issue in the repository or contact the maintainer.

