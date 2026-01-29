package snaddy.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import snaddy.command.AddCommand;
import snaddy.command.Command;
import snaddy.command.DeleteCommand;
import snaddy.command.ExitCommand;
import snaddy.command.FindCommand;
import snaddy.command.ListCommand;
import snaddy.command.MarkCommand;
import snaddy.command.OnCommand;
import snaddy.command.UnmarkCommand;
import snaddy.exception.SnaddyException;
import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.ToDo;

/**
 * Parses user input strings into Command objects.
 * Handles parsing of all command types and their arguments.
 */
public class Parser {

    /**
     * Parses a full command string and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A Command object corresponding to the parsed command.
     * @throws SnaddyException If the command is invalid or arguments are malformed.
     */
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
        case "find":
            return parseFindCommand(arguments);
        default:
            throw new SnaddyException("SAD!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a task number from the command arguments.
     *
     * @param arguments The arguments string containing the task number.
     * @return The zero-based index of the task.
     * @throws SnaddyException If the arguments are empty or not a valid number.
     */
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

    /**
     * Parses a todo command and creates the corresponding AddCommand.
     *
     * @param arguments The arguments string containing the todo description.
     * @return An AddCommand with a ToDo task.
     * @throws SnaddyException If the description is empty.
     */
    private static Command parseTodoCommand(String arguments) throws SnaddyException {
        String description = arguments.trim();
        if (description.isEmpty()) {
            throw new SnaddyException("SAD!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(description));
    }

    /**
     * Parses a deadline command and creates the corresponding AddCommand.
     *
     * @param arguments The arguments string containing description and /by date.
     * @return An AddCommand with a Deadline task.
     * @throws SnaddyException If the arguments are malformed or missing required parts.
     */
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

    /**
     * Parses an event command and creates the corresponding AddCommand.
     *
     * @param arguments The arguments string containing description, /from date,     and /to date.
     * @return An AddCommand with an Event task.
     * @throws SnaddyException If the arguments are malformed or missing required parts.
     */
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

    /**
     * Parses an on command and creates the corresponding OnCommand.
     *
     * @param arguments The arguments string containing the date.
     * @return An OnCommand with the parsed date.
     * @throws SnaddyException If the date string is empty or in invalid format.
     */
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

    /**
     * Parses a find command and creates the corresponding FindCommand.
     *
     * @param arguments The arguments string containing the keyword.
     * @return A FindCommand with the parsed keyword.
     * @throws SnaddyException If the keyword is empty.
     */
    private static Command parseFindCommand(String arguments) throws SnaddyException {
        String keyword = arguments.trim();
        if (keyword.isEmpty()) {
            throw new SnaddyException("SAD!!! Please specify a keyword to find.");
        }
        return new FindCommand(keyword);
    }
}
