package snaddy.util;

import java.util.Scanner;

import snaddy.task.Task;

/**
 * Handles user interface operations including displaying messages and reading user input.
 */
public class Ui {
    private static final String DIVIDER = "      _________________________________________________\n";

    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with the application DIVIDER.
     */
    public String showWelcome() {
        return DIVIDER + "      Hello! I'm Snaddy\n"
                + "      What can I do for you?\n"
                + DIVIDER;
    }

    /**
     * Displays a divider line.
     */
    public String showLine() {
        return DIVIDER;
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return "      " + message + "\n";
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from file.
     */
    public String showLoadingError() {
        return showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public String showGoodbye() {
        return "      Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after adding.
     */
    public String showTaskAdded(Task task, int taskCount) {
        assert task != null : "task to display should not be null";
        assert taskCount > 0 : "task count should be positive after add";
        return "      Got it. I've added this task:\n"
                + "        " + task + "\n"
                + "      Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        assert task != null : "deleted task should not be null";
        assert taskCount >= 0 : "task count should be non-negative after delete";
        return "      Noted. I've removed this task:\n"
                + "        " + task + "\n"
                + "      Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showTaskMarked(Task task) {
        assert task != null : "task to display should not be null";
        return "      Nice! I've marked this task as done:\n"
                + "        " + task + "\n";
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "task to display should not be null";
        return "      OK, I've marked this task as not done yet:\n"
                + "        " + task + "\n";
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList to display.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "task list to display should not be null";
        StringBuilder result = new StringBuilder();
        result.append("      Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("      ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Displays tasks that occur on a specific date.
     *
     * @param matchingTasks The TaskList containing tasks that match the date.
     * @param dateString The formatted date string to display.
     */
    public String showTasksOnDate(TaskList matchingTasks, String dateString) {
        assert matchingTasks != null : "matching task list should not be null";
        assert dateString != null : "date string for display should not be null";
        StringBuilder result = new StringBuilder();
        result.append("      Here are the tasks on ").append(dateString).append(":\n");
        if (matchingTasks.isEmpty()) {
            result.append("      No tasks found.\n");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append("      ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Displays tasks whose descriptions match a keyword search.
     *
     * @param matchingTasks The TaskList containing matching tasks.
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        assert matchingTasks != null : "matching task list should not be null";
        StringBuilder result = new StringBuilder();
        result.append("      Here are the matching tasks in your list:\n");
        if (matchingTasks.isEmpty()) {
            result.append("      No tasks found.\n");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append("      ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Displays the help page listing available commands and usage.
     *
     * @return The help page message.
     */
    public String showHelp() {
        StringBuilder result = new StringBuilder();
        result.append("      SAD!!! Here are the commands you can use:\n");
        result.append("      ____________________________________________________________\n");
        result.append("      list\n");
        result.append("        Show all tasks in your list.\n");
        result.append("      ____________________________________________________________\n");
        result.append("      todo <description>\n");
        result.append("        Add a todo task. Example: todo read book\n");
        result.append("      ____________________________________________________________\n");
        result.append("      deadline <description> /by <date>\n");
        result.append("        Add a deadline. Date format: yyyy-mm-dd.\n");
        result.append("        Example: deadline return book /by 2025-02-10\n");
        result.append("      ____________________________________________________________\n");
        result.append("      event <description> /from <date> /to <date>\n");
        result.append("        Add an event. Example: event meeting /from 2025-02-05 /to 2025-02-05\n");
        result.append("      ____________________________________________________________\n");
        result.append("      mark <task number>\n");
        result.append("        Mark a task as done. Example: mark 1\n");
        result.append("      ____________________________________________________________\n");
        result.append("      unmark <task number>\n");
        result.append("        Mark a task as not done. Example: unmark 1\n");
        result.append("      ____________________________________________________________\n");
        result.append("      delete <task number>\n");
        result.append("        Remove a task from the list. Example: delete 1\n");
        result.append("      ____________________________________________________________\n");
        result.append("      find <keyword>\n");
        result.append("        Search for tasks by keyword. Example: find book\n");
        result.append("      ____________________________________________________________\n");
        result.append("      on <date>\n");
        result.append("        Show tasks on a date (yyyy-mm-dd). Example: on 2025-02-05\n");
        result.append("      ____________________________________________________________\n");
        result.append("      help\n");
        result.append("        Show this help page.\n");
        result.append("      ____________________________________________________________\n");
        result.append("      bye\n");
        result.append("        Exit the application.\n");
        result.append("      ____________________________________________________________\n");
        return result.toString();
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
