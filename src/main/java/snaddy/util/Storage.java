package snaddy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import snaddy.exception.SnaddyException;
import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.Task;
import snaddy.task.ToDo;

/**
 * Handles loading and saving of tasks to/from a file.
 * Manages file I/O operations and task serialization/deserialization.
 */
public class Storage {
    private static final String DELIMITER = " | ";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_DONE_MARKER = "1";
    private static final int MIN_PARTS_COUNT = 3;
    private static final int DEADLINE_PARTS_COUNT = 4;
    private static final int EVENT_PARTS_COUNT = 5;

    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     * Creates the directory and file if they do not exist.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws SnaddyException If an error occurs while reading from the file.
     */
    public ArrayList<Task> load() throws SnaddyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new SnaddyException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a single line from the file into a Task object.
     *
     * @param line The line to parse in the format "type | isDone | description | ...".
     * @return The parsed Task object, or null if parsing fails.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < MIN_PARTS_COUNT) {
                return null;
            }

            String type = parts[0];
            boolean isDone = parts[1].equals(TASK_DONE_MARKER);
            String description = parts[2];

            Task task = null;
            switch (type) {
            case TASK_TYPE_TODO:
                task = new ToDo(description);
                break;
            case TASK_TYPE_DEADLINE:
                if (parts.length >= DEADLINE_PARTS_COUNT) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case TASK_TYPE_EVENT:
                if (parts.length >= EVENT_PARTS_COUNT) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
            default:
                return null;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }

            return task;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Saves the given list of tasks to the file specified in the file path.
     * Creates the directory and file if they do not exist.
     *
     * @param tasks The list of tasks to be saved.
     * @throws SnaddyException If an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws SnaddyException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(formatTask(task) + "\n");
                }
            }
        } catch (IOException e) {
            throw new SnaddyException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Formats a Task object into a string representation for file storage.
     *
     * @param task The task to format.
     * @return A formatted string representation of the task.
     */
    private String formatTask(Task task) {
        String type;
        String isDone = task.isDone() ? TASK_DONE_MARKER : "0";
        String description = task.getDescription();
        StringBuilder formatted = new StringBuilder();

        if (task instanceof ToDo) {
            type = TASK_TYPE_TODO;
            formatted.append(type).append(DELIMITER).append(isDone).append(DELIMITER).append(description);
        } else if (task instanceof Deadline) {
            type = TASK_TYPE_DEADLINE;
            Deadline deadline = (Deadline) task;
            formatted.append(type).append(DELIMITER).append(isDone).append(DELIMITER)
                    .append(description).append(DELIMITER).append(deadline.getBy());
        } else if (task instanceof Event) {
            type = TASK_TYPE_EVENT;
            Event event = (Event) task;
            formatted.append(type).append(DELIMITER).append(isDone).append(DELIMITER)
                    .append(description).append(DELIMITER).append(event.getFrom())
                    .append(DELIMITER).append(event.getTo());
        }

        return formatted.toString();
    }
}
