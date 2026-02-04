package snaddy.command;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks The task list to display.
     * @param ui The UI handler for displaying the task list.
     * @param storage The storage handler (not used).
     * @return The output message to be shown to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null : "dependencies should be provided";
        return ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
