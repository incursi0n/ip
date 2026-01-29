package snaddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time string (preferably in yyyy-mm-dd format).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = parseDate(by);
    }

    /**
     * Attempts to parse a date string into a LocalDate object.
     *
     * @param dateString The date string to parse (expected format: yyyy-mm-dd).
     * @return The parsed LocalDate, or null if parsing fails.
     */
    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns a formatted date string if the date was successfully parsed, otherwise returns the original string.
     *
     * @return A formatted date string (MMM d yyyy) or the original date string if parsing failed.
     */
    public String getDateString() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return by;
    }

    /**
     * Returns the parsed LocalDate object, or null if parsing failed.
     *
     * @return The LocalDate representation of the due date, or null if parsing failed.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the original due date string.
     *
     * @return The original due date string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string in the format "[D][status] description (by: date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString() + ")";
    }
}
