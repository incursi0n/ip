package snaddy.command;

import snaddy.exception.SnaddyException;
import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnaddyException;

    public abstract boolean isExit();
}