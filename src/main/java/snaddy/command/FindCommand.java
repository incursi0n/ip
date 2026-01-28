package snaddy.command;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

/**
 * Represents a command to find tasks by searching for a keyword in their descriptions.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks whose descriptions contain the keyword.
     *
     * @param tasks   The task list to search.
     * @param ui      The UI handler for displaying matching tasks.
     * @param storage The storage handler (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the keyword associated with this command.
     *
     * @return The keyword used for searching.
     */
    public String getKeyword() {
        return keyword;
    }
}

