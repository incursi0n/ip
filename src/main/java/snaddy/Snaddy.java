package snaddy;

import java.io.File;

import snaddy.command.Command;
import snaddy.exception.SnaddyException;
import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.ToDo;
import snaddy.util.Parser;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents the main application class for Snaddy, a task management application.
 * Handles initialization, command processing, and the main execution loop.
 */
public class Snaddy {
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "snaddy.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final String startupMessage;
    private boolean isExit;

    /**
     * Constructs a Snaddy instance with the specified file path for data storage.
     * Initializes the UI, storage, and task list. If loading fails, starts with an empty task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Snaddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        String message = "";
        boolean isFirstRun = !storage.fileExists();
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (SnaddyException e) {
            loadedTasks = new TaskList();
            message = ui.showLine()
                    + ui.showLoadingError()
                    + ui.showLine();
        }
        if (isFirstRun && loadedTasks.isEmpty()) {
            loadSampleData(loadedTasks);
            try {
                storage.save(loadedTasks.getTasks());
            } catch (SnaddyException e) {
                message = message + ui.showLine()
                        + ui.showError("Could not save sample data: " + e.getMessage())
                        + ui.showLine();
            }
        }
        tasks = loadedTasks;
        startupMessage = message;
        assert tasks != null : "task list should be initialized";
        assert storage != null : "storage should be initialized";
        assert ui != null : "ui should be initialized";
    }

    /**
     * Constructs a Snaddy instance using the default file path for data storage.
     */
    public Snaddy() {
        this(FILE_PATH);
    }

    /**
     * Loads sample tasks into the given task list for first-run guidance.
     *
     * @param taskList The task list to add sample tasks to.
     */
    private void loadSampleData(TaskList taskList) {
        taskList.add(new ToDo("Try the help command"));
        taskList.add(new ToDo("Add a todo with: todo <description>"));
        taskList.add(new Deadline("Sample deadline", "2025-12-31"));
        taskList.add(new Event("Sample event", "2025-06-01", "2025-06-02"));
        taskList.get(1).markAsDone();
    }

    /**
     * Returns the welcome message to be shown to the user.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome() + startupMessage;
    }

    /**
     * Returns whether the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Generates a response for the user's input, updating application state as needed.
     *
     * @param input The full command string entered by the user.
     * @return The response message to be shown to the user.
     */
    public String getResponse(String input) {
        assert input != null : "user input should not be null";
        try {
            Command command = Parser.parse(input);
            assert command != null : "parser should return a command for valid input";
            String response = command.execute(tasks, ui, storage);
            isExit = command.isExit();
            return ui.showLine() + response + ui.showLine();
        } catch (SnaddyException e) {
            return ui.showLine() + ui.showError(e.getMessage()) + ui.showLine();
        } catch (Exception e) {
            return ui.showLine()
                    + ui.showError("SAD!!! An error occurred: " + e.getMessage())
                    + ui.showLine();
        }
    }

    /**
     * Runs the application using the command-line interface.
     * Displays welcome message, reads and processes user commands until exit command is received.
     */
    public void run() {
        System.out.print(getWelcomeMessage());
        while (!isExit) {
            String fullCommand = ui.readCommand();
            assert fullCommand != null : "readCommand should return a string (possibly empty)";
            System.out.print(getResponse(fullCommand));
        }
        ui.close();
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Snaddy(FILE_PATH).run();
    }
}
