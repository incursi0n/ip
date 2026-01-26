package snaddy.util;

import snaddy.task.Task;

import java.util.Scanner;

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

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.print(LOGO + "      Hello! I'm Snaddy\n      What can I do for you?\n" + DIVIDER);
    }

    public void showLine() {
        System.out.print(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("      " + message);
    }

    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    public void showGoodbye() {
        System.out.println("      Bye. Hope to see you again soon!");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("      Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.println("      Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("      Noted. I've removed this task:");
        System.out.println("        " + task);
        System.out.println("      Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("      Nice! I've marked this task as done:");
        System.out.println("        " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("      OK, I've marked this task as not done yet:");
        System.out.println("        " + task);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("      Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.get(i));
        }
    }

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

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}