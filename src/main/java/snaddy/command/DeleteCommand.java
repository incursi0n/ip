package snaddy.command;

import snaddy.exception.SnaddyException;
import snaddy.task.Task;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task at the specified index and saving to storage.
     *
     * @param tasks The task list to remove the task from.
     * @param ui The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws SnaddyException If the task index is invalid or an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new SnaddyException("SAD!!! Task number " + (taskIndex + 1)
                    + " does not exist. You have " + tasks.size() + " task(s) in your list.");
        }
        Task deletedTask = tasks.remove(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(deletedTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
