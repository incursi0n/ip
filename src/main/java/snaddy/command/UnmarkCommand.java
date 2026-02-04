package snaddy.command;

import snaddy.exception.SnaddyException;
import snaddy.task.Task;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The zero-based index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done and saving to storage.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param ui The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @return The output message to be shown to the user.
     * @throws SnaddyException If the task index is invalid or an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException {
        assert tasks != null && ui != null && storage != null : "tasks, ui, storage should not be null";
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new SnaddyException("SAD!!! Task number " + (taskIndex + 1)
                    + " does not exist. You have " + tasks.size() + " task(s) in your list.");
        }
        assert taskIndex >= 0 && taskIndex < tasks.size() : "task index should be valid after check";
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks.getTasks());
        return ui.showTaskUnmarked(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
