package snaddy.command;

import snaddy.util.Storage;
import snaddy.util.TaskList;
import snaddy.util.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OnCommand extends Command {
    private LocalDate date;

    public OnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasksOnDate(date);
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        ui.showTasksOnDate(matchingTasks, dateString);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public LocalDate getDate() {
        return date;
    }
}