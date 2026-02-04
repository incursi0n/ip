package snaddy.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to find and display tasks on a specific date.
 */
public class OnCommand extends Command {
    private LocalDate date;

    /**
     * Constructs an OnCommand with the specified date.
     *
     * @param date The date to search for tasks.
     */
    public OnCommand(LocalDate date) {
        assert date != null : "date should not be null";
        this.date = date;
    }

    /**
     * Executes the on command by finding tasks on the specified date and displaying them.
     *
     * @param tasks The task list to search.
     * @param ui The UI handler for displaying the matching tasks.
     * @param storage The storage handler (not used).
     * @return The output message to be shown to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && ui != null && storage != null : "tasks, ui, storage should not be null";
        TaskList matchingTasks = tasks.findTasksOnDate(date);
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return ui.showTasksOnDate(matchingTasks, dateString);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the date associated with this command.
     *
     * @return The date to search for tasks.
     */
    public LocalDate getDate() {
        return date;
    }
}
