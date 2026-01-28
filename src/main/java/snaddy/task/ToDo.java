package snaddy.task;

/**
 * Represents a todo task without any date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}