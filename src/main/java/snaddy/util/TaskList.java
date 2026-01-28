package snaddy.util;

import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.Task;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskList findTasksOnDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate() != null && deadline.getDate().equals(date)) {
                    matchingTasks.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartDate() != null && event.getEndDate() != null) {
                    if ((date.equals(event.getStartDate()) || date.equals(event.getEndDate())
                            || (date.isAfter(event.getStartDate()) && date.isBefore(event.getEndDate())))) {
                        matchingTasks.add(task);
                    }
                }
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Finds and returns all tasks whose descriptions contain the specified keyword (case-insensitive).
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing all matching tasks.
     */
    public TaskList findTasksByKeyword(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}