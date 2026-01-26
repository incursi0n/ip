public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException;
    public abstract boolean isExit();
}