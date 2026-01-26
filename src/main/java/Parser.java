import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws SnaddyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskNumber(arguments));
            case "unmark":
                return new UnmarkCommand(parseTaskNumber(arguments));
            case "delete":
                return new DeleteCommand(parseTaskNumber(arguments));
            case "todo":
                return parseTodoCommand(arguments);
            case "deadline":
                return parseDeadlineCommand(arguments);
            case "event":
                return parseEventCommand(arguments);
            case "on":
                return parseOnCommand(arguments);
            default:
                throw new SnaddyException("SAD!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseTaskNumber(String arguments) throws SnaddyException {
        if (arguments.trim().isEmpty()) {
            throw new SnaddyException("SAD!!! Please specify a task number.");
        }
        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new SnaddyException("SAD!!! Please provide a valid task number.");
        }
    }

    private static Command parseTodoCommand(String arguments) throws SnaddyException {
        String description = arguments.trim();
        if (description.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(description));
    }

    private static Command parseDeadlineCommand(String arguments) throws SnaddyException {
        String details = arguments.trim();
        if (details.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of a deadline cannot be empty.");
        }
        int byIndex = details.indexOf(" /by ");
        if (byIndex == -1) {
            throw new SnaddyException("SAD!!! Please specify the deadline using /by.\n"
                    + "      Usage: deadline <description> /by <date>\n"
                    + "      Date format: yyyy-mm-dd (e.g., 2019-12-02)");
        }
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + 5).trim();
        if (description.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new SnaddyException("SAD!!! The deadline date/time cannot be empty.");
        }
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEventCommand(String arguments) throws SnaddyException {
        String details = arguments.trim();
        if (details.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of an event cannot be empty.");
        }
        int fromIndex = details.indexOf(" /from ");
        int toIndex = details.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new SnaddyException("SAD!!! Please specify the event using /from and /to.\n"
                    + "      Usage: event <description> /from <date> /to <date>\n"
                    + "      Date format: yyyy-mm-dd (e.g., 2019-12-02)");
        }
        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromIndex + 7, toIndex).trim();
        String to = details.substring(toIndex + 5).trim();
        if (description.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of an event cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new SnaddyException("SAD!!! The event start time cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new SnaddyException("SAD!!! The event end time cannot be empty.");
        }
        return new AddCommand(new Event(description, from, to));
    }

    private static Command parseOnCommand(String arguments) throws SnaddyException {
        String dateString = arguments.trim();
        if (dateString.isEmpty()) {
            throw new SnaddyException("SAD!!! Please specify a date.\n"
                    + "      Usage: on <date>\n"
                    + "      Date format: yyyy-mm-dd (e.g., 2019-12-02)");
        }
        try {
            LocalDate date = LocalDate.parse(dateString);
            return new OnCommand(date);
        } catch (DateTimeParseException e) {
            throw new SnaddyException("SAD!!! Please provide date in yyyy-mm-dd format.\n"
                    + "      Example: on 2019-12-02");
        }
    }
}