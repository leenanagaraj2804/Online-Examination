import java.util.*;

class OnlineExamination {

    static Scanner sc = new Scanner(System.in);

    // User data (demo purpose)
    static String username = "student";
    static String password = "1234";

    static String name = "Student Name";

    static boolean loggedIn = false;

    // Questions
    static String[] questions = {
            "1. Java is a ___ language?\nA. Programming\nB. Markup\nC. Styling\nD. None",
            "2. Which keyword is used for inheritance?\nA. this\nB. super\nC. extends\nD. implements",
            "3. JVM stands for?\nA. Java Variable Machine\nB. Java Virtual Machine\nC. Just Virtual Machine\nD. None"
    };

    static char[] answers = {'A', 'C', 'B'};

    static char[] userAnswers = new char[questions.length];

    public static void main(String[] args) {

        while (true) {
            if (!loggedIn) {
                login();
            } else {
                menu();
            }
        }
    }

    // 🔐 Login
    static void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        if (u.equals(username) && p.equals(password)) {
            loggedIn = true;
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    // 📋 Menu
    static void menu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Update Profile & Password");
        System.out.println("2. Start Exam");
        System.out.println("3. Logout");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                updateProfile();
                break;
            case 2:
                startExam();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }

    // 👤 Update Profile
    static void updateProfile() {
        System.out.println("\n--- UPDATE PROFILE ---");
        System.out.print("Enter New Name: ");
        name = sc.next();

        System.out.print("Enter Old Password: ");
        String oldPass = sc.next();

        if (oldPass.equals(password)) {
            System.out.print("Enter New Password: ");
            password = sc.next();
            System.out.println("Profile Updated Successfully!");
        } else {
            System.out.println("Wrong Password!");
        }
    }

    // 📝 Start Exam with Timer
    static void startExam() {
        System.out.println("\n--- EXAM STARTED ---");
        System.out.println("You have 30 seconds!");

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\n⏰ Time Up! Auto Submitting...");
                submitExam();
                System.exit(0);
            }
        }, 30000); // 30 seconds

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n" + questions[i]);
            System.out.print("Your Answer: ");
            userAnswers[i] = sc.next().toUpperCase().charAt(0);
        }

        timer.cancel();
        submitExam();
    }

    // ✅ Submit Exam
    static void submitExam() {
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] == answers[i]) {
                score++;
            }
        }

        System.out.println("\n--- RESULT ---");
        System.out.println("Score: " + score + "/" + questions.length);
    }

    // 🚪 Logout
    static void logout() {
        loggedIn = false;
        System.out.println("Logged Out Successfully!");
    }
}