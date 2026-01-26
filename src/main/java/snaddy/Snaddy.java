package snaddy;

import snaddy.command.Command;
import snaddy.exception.SnaddyException;
import snaddy.util.Parser;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

import java.io.File;

public class Snaddy {
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "snaddy.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Snaddy(FILE_PATH).run();
    }
}