package snaddy.util;

import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.Task;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks with operations to manage them.
 * Provides methods to add, remove, get, and search tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The zero-based index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds and returns all tasks that occur on the specified date.
     * For deadlines, matches tasks with the exact due date.
     * For events, matches tasks where the date falls within the event's start and end dates (inclusive).
     *
     * @param date The date to search for tasks.
     * @return A new TaskList containing all matching tasks.
     */
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
}