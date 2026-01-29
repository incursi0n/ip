package snaddy;

import java.io.File;

import snaddy.command.Command;
import snaddy.exception.SnaddyException;
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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Snaddy instance with the specified file path for data storage.
     * Initializes the UI, storage, and task list. If loading fails, starts with an empty task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Snaddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SnaddyException e) {
            ui.showLine();
            ui.showLoadingError();
            ui.showLine();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Displays welcome message, reads and processes user commands until exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SnaddyException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("SAD!!! An error occurred: " + e.getMessage());
            } finally {
                ui.showLine();
            }
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
