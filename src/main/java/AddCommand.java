public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}