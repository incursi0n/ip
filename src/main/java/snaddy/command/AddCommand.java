package snaddy.command;

import snaddy.exception.SnaddyException;
import snaddy.task.Task;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "task to add should not be null";
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list and saving to storage.
     *
     * @param tasks The task list to add the task to.
     * @param ui The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @return The output message to be shown to the user.
     * @throws SnaddyException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException {
        assert task != null : "task to add should not be null";
        assert tasks != null && storage != null && ui != null : "dependencies should be provided";
        tasks.add(task);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the task associated with this command.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return task;
    }
}
