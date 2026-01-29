package snaddy.util;

import java.util.Scanner;

import snaddy.task.Task;

/**
 * Handles user interface operations including displaying messages and reading user input.
 */
public class Ui {
    private static final String DIVIDER = "      ____________________________________________________________\n";
    private static final String LOGO = DIVIDER
            + "       _________                  .___  .___      \n"
            + "      /   _____/ ____ _____     __| _/__| _/__.__.\n"
            + "      \\_____  \\ /    \\\\__  \\   / __ |/ __ <   |  |\n"
            + "       /        \\   |  \\/ __ \\_/ /_/ / /_/ |\\___  |\n"
            + "       /_______  /___|  (____  /\\____ \\____ |/ ____|\n"
            + "               \\/     \\/     \\/      \\/    \\/\\/     \n";

    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with the application logo.
     */
    public void showWelcome() {
        System.out.print(LOGO + "      Hello! I'm Snaddy\n      What can I do for you?\n" + DIVIDER);
    }

    /**
     * Displays a divider line.
     */
    public void showLine() {
        System.out.print(DIVIDER);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("      " + message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from file.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("      Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after adding.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("      Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.println("      Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("      Noted. I've removed this task:");
        System.out.println("        " + task);
        System.out.println("      Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("      Nice! I've marked this task as done:");
        System.out.println("        " + task);
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("      OK, I've marked this task as not done yet:");
        System.out.println("        " + task);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("      Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays tasks that occur on a specific date.
     *
     * @param matchingTasks The TaskList containing tasks that match the date.
     * @param dateString The formatted date string to display.
     */
    public void showTasksOnDate(TaskList matchingTasks, String dateString) {
        System.out.println("      Here are the tasks on " + dateString + ":");
        if (matchingTasks.size() == 0) {
            System.out.println("      No tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("      " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Displays tasks whose descriptions match a keyword search.
     *
     * @param matchingTasks The TaskList containing matching tasks.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        System.out.println("      Here are the matching tasks in your list:");
        if (matchingTasks.size() == 0) {
            System.out.println("      No tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("      " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Reads a command from the user.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
