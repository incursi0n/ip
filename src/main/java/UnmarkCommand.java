public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new SnaddyException("OOPS!!! Task number " + (taskIndex + 1)
                    + " does not exist. You have " + tasks.size() + " task(s) in your list.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}