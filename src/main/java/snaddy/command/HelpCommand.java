package snaddy.command;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to display in-app help with available commands and usage.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by displaying the help page.
     *
     * @param tasks The task list (not used).
     * @param ui The UI handler for displaying the help page.
     * @param storage The storage handler (not used).
     * @return The output message to be shown to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "ui should be provided";
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
