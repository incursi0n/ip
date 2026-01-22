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

            try {
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
                    if (input.trim().length() <= 5) {
                        throw new SnaddyException("SAD!!! Please specify which task to mark.\n"
                                + "      Usage: mark <task number>");
                    }
                    int taskNumber = parseTaskNumber(input.substring(5), taskCount);
                    tasks[taskNumber].markAsDone();
                    System.out.println(divider + "      Nice! I've marked this task as done:\n"
                            + "        " + tasks[taskNumber] + "\n" + divider);
                } else if (input.startsWith("unmark ")) {
                    if (input.trim().length() <= 7) {
                        throw new SnaddyException("SAD!!! Please specify which task to unmark.\n"
                                + "      Usage: unmark <task number>");
                    }
                    int taskNumber = parseTaskNumber(input.substring(7), taskCount);
                    tasks[taskNumber].markAsNotDone();
                    System.out.println(divider + "      OK, I've marked this task as not done yet:\n"
                            + "        " + tasks[taskNumber] + "\n" + divider);
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new SnaddyException("SAD!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new ToDo(description);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println(divider + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
                } else if (input.startsWith("deadline ")) {
                    String details = input.substring(9).trim();
                    if (details.isEmpty()) {
                        throw new SnaddyException("SAD!!! The description of a deadline cannot be empty.");
                    }
                    int byIndex = details.indexOf(" /by ");
                    if (byIndex == -1) {
                        throw new SnaddyException("SAD!!! Please specify the deadline using /by.\n"
                                + "      Usage: deadline <description> /by <date/time>");
                    }
                    String description = details.substring(0, byIndex).trim();
                    String by = details.substring(byIndex + 5).trim();
                    if (description.isEmpty()) {
                        throw new SnaddyException("SAD!!! The description of a deadline cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new SnaddyException("SAD!!! The deadline date/time cannot be empty.");
                    }
                    Task newTask = new Deadline(description, by);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println(divider + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
                } else if (input.startsWith("event ")) {
                    String details = input.substring(6).trim();
                    if (details.isEmpty()) {
                        throw new SnaddyException("SAD!!! The description of an event cannot be empty.");
                    }
                    int fromIndex = details.indexOf(" /from ");
                    int toIndex = details.indexOf(" /to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new SnaddyException("SAD!!! Please specify the event using /from and /to.\n"
                                + "      Usage: event <description> /from <start time> /to <end time>");
                    }
                    String description = details.substring(0, fromIndex).trim();
                    String from = details.substring(fromIndex + 7, toIndex).trim();
                    String to = details.substring(toIndex + 5).trim();
                    if (description.isEmpty()) {
                        throw new SnaddyException("SAD!!! The description of an event cannot be empty.");
                    }
                    if (from.isEmpty()) {
                        throw new SnaddyException("SAD!!! The event start time cannot be empty.");
                    }
                    if (to.isEmpty()) {
                        throw new SnaddyException("SAD!!! The event end time cannot be empty.");
                    }
                    Task newTask = new Event(description, from, to);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println(divider + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskCount + " tasks in the list.\n" + divider);
                } else if (input.trim().equals("todo") || input.trim().equals("deadline")
                        || input.trim().equals("event")) {
                    throw new SnaddyException("SAD!!! The description cannot be empty.\n"
                            + "      Please provide details for your " + input.trim() + ".");
                } else if (input.trim().equals("mark") || input.trim().equals("unmark")) {
                    throw new SnaddyException("SAD!!! Please specify which task number.\n"
                            + "      Usage: " + input.trim() + " <task number>");
                } else {
                    throw new SnaddyException("SAD!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (SnaddyException e) {
                System.out.println(divider + "      " + e.getMessage() + "\n" + divider);
            } catch (NumberFormatException e) {
                System.out.println(divider + "      SAD!!! Please provide a valid task number.\n" + divider);
            } catch (Exception e) {
                System.out.println(divider + "      SAD!!! An error occurred: " + e.getMessage() + "\n" + divider);
            }
        }

        scanner.close();
    }

    private static int parseTaskNumber(String input, int taskCount) throws SnaddyException {
        try {
            int taskNumber = Integer.parseInt(input.trim()) - 1;
            if (taskNumber < 0 || taskNumber >= taskCount) {
                throw new SnaddyException("SAD!!! Task number " + (taskNumber + 1)
                        + " does not exist. You have " + taskCount + " task(s) in your list.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new SnaddyException("SAD!!! Please provide a valid task number.");
        }
    }
}
