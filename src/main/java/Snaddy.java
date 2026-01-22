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
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task newTask = new ToDo(description);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println(divider + "      Got it. I've added this task:\n"
                        + "        " + newTask + "\n"
                        + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
            } else if (input.startsWith("deadline ")) {
                String details = input.substring(9);
                int byIndex = details.indexOf(" /by ");
                if (byIndex == -1) {
                    System.out.println(divider + "      Error: Please specify deadline using /by\n"
                            + "      Example: deadline return book /by Sunday\n" + divider);
                } else {
                    String description = details.substring(0, byIndex);
                    String by = details.substring(byIndex + 5);
                    Task newTask = new Deadline(description, by);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println(divider + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
                }
            } else if (input.startsWith("event ")) {
                String details = input.substring(6);
                int fromIndex = details.indexOf(" /from ");
                int toIndex = details.indexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1) {
                    System.out.println(divider + "      Error: Please specify event using /from and /to\n"
                            + "      Example: event project meeting /from Mon 2pm /to 4pm\n" + divider);
                } else {
                    String description = details.substring(0, fromIndex);
                    String from = details.substring(fromIndex + 7, toIndex);
                    String to = details.substring(toIndex + 5);
                    Task newTask = new Event(description, from, to);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println(divider + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
                }
            } else {
                System.out.println(divider + "      I don't understand that command.\n" + divider);
            }
        }

        scanner.close();
    }
}
