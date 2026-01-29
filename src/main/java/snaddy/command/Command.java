package snaddy.command;

import snaddy.exception.SnaddyException;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * All command classes must extend this class and implement the execute and isExit methods.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI handler for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws SnaddyException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if the command should exit the application, false otherwise.
     */
    public abstract boolean isExit();
}
