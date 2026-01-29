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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
