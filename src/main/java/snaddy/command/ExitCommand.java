package snaddy.command;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks The task list (not used).
     * @param ui The UI handler for displaying the goodbye message.
     * @param storage The storage handler (not used).
     * @return The output message to be shown to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "ui should be provided";
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
