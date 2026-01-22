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
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(divider + "      " + goodbye + divider);
            } else if (input.equals("list")) {
                System.out.println(divider);
                System.out.println("      Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("      " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(divider);
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(divider + "      Nice! I've marked this task as done:\n"
                        + "        " + tasks[taskNumber] + "\n" + divider);
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(divider + "      OK, I've marked this task as not done yet:\n"
                        + "        " + tasks[taskNumber] + "\n" + divider);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(divider + "      added: " + input + "\n" + divider);
            }
        }

        scanner.close();
    }
}
