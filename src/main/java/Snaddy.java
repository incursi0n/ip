import java.util.Scanner;

public class Snaddy {
    public static void main(String[] args) {
        String divider = "      ____________________________________________________________\n";
        String logo = divider
                + "       _________                  .___  .___      \n"
                + "      /   _____/ ____ _____     __| _/__| _/__.__.\n"
                + "      \\_____  \\ /    \\\\__  \\   / __ |/ __ <   |  |\n"
                + "       /        \\   |  \\/ __ \\_/ /_/ / /_/ |\\___  |\n"
                + "       /_______  /___|  (____  /\\____ \\____ |/ ____|\n"
                + "               \\/     \\/     \\/      \\/    \\/\\/     \n";
        String goodbye = "Bye. Hope to see you again soon!\n";

        System.out.print(logo + "      Hello! I'm Snaddy\n      What can I do for you?\n" + divider);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(divider + "      " + goodbye + divider);
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("      " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(divider);
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(divider + "      added: " + input + "\n" + divider);
            }
        }

        scanner.close();
    }
}
