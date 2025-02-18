
import java.util.HashMap;
import java.util.Scanner;

public class OnlineExamination {
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private static HashMap<String, String> userProfile = new HashMap<>();
    private static boolean isLoggedIn = false;
    private static String currentUser = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeDatabase();

        while (true) {
            if (!isLoggedIn) {
                System.out.println("\nWelcome to Online Examination System");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                if (choice == 1) {
                    login(scanner);
                } else if (choice == 2) {
                    System.out.println("Thank you for using the Online Examination System. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                showMenu(scanner);
            }
        }
        scanner.close();  // Close the scanner at the end of the program
    }

    private static void initializeDatabase() {
        userDatabase.put("user1", "password1");
        userProfile.put("user1", "Name: John Doe, Email: john@example.com");

        userDatabase.put("user2", "password2");
        userProfile.put("user2", "Name: Jane Smith, Email: jane@example.com");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            isLoggedIn = true;
            currentUser = username;
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void showMenu(Scanner scanner) {
        System.out.println("\nMain Menu");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile and Password");
        System.out.println("3. Take MCQ Exam");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        switch (choice) {
            case 1:
                viewProfile();
                break;
            case 2:
                updateProfile(scanner);
                break;
            case 3:
                takeExam(scanner);
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewProfile() {
        System.out.println("Your Profile: " + userProfile.get(currentUser));
    }

    private static void updateProfile(Scanner scanner) {
        System.out.print("Enter new profile information: ");
        String newProfile = scanner.nextLine();
        userProfile.put(currentUser, newProfile);
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userDatabase.put(currentUser, newPassword);
        System.out.println("Profile and password updated successfully.");
    }

    private static void takeExam(Scanner scanner) {
        System.out.println("MCQ Exam: Answer the following questions (type the correct option number):");
        String[] questions = {
            "Q1: What is the capital of France?\n1. Berlin\n2. Madrid\n3. Paris\n4. Rome",
            "Q2: Which programming language is used for Android development?\n1. Python\n2. Java\n3. C++\n4. Ruby",
            "Q3: What is 5 + 3?\n1. 5\n2. 8\n3. 10\n4. 7"
        };
        int[] answers = {3, 2, 2};
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();

            // Check if the answer is within the valid range of options (1-4)
            if (answer < 1 || answer > 4) {
                System.out.println("Invalid answer. Please select a valid option (1-4).");
                i--; // Retry the current question
                continue;
            }

            if (answer == answers[i]) {
                score++;
            }
        }
        System.out.println("Exam completed. Your score: " + score + "/" + questions.length);
    }

    private static void logout() {
        isLoggedIn = false;
        currentUser = "";
        System.out.println("You have been logged out successfully.");
    }
}
