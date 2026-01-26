import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws SnaddyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new SnaddyException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null;
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        task = new Deadline(description, parts[3]);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
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

    public void save(ArrayList<Task> tasks) throws SnaddyException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new SnaddyException("Error saving tasks to file: " + e.getMessage());
        }
    }

    private String formatTask(Task task) {
        String type;
        String isDone = task.isDone ? "1" : "0";
        String description = task.getDescription();
        StringBuilder formatted = new StringBuilder();

        if (task instanceof ToDo) {
            type = "T";
            formatted.append(type).append(" | ").append(isDone).append(" | ").append(description);
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            formatted.append(type).append(" | ").append(isDone).append(" | ")
                    .append(description).append(" | ").append(deadline.by);
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            formatted.append(type).append(" | ").append(isDone).append(" | ")
                    .append(description).append(" | ").append(event.from)
                    .append(" | ").append(event.to);
        }

        return formatted.toString();
    }
}
