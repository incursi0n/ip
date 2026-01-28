package snaddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start date and end date.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date/time string (preferably in yyyy-mm-dd format).
     * @param to The end date/time string (preferably in yyyy-mm-dd format).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.startDate = parseDate(from);
        this.endDate = parseDate(to);
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
     * Returns a formatted start date string if the date was successfully parsed, otherwise returns the original string.
     *
     * @return A formatted date string (MMM d yyyy) or the original start date string if parsing failed.
     */
    public String getFromString() {
        if (startDate != null) {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return from;
    }

    /**
     * Returns a formatted end date string if the date was successfully parsed, otherwise returns the original string.
     *
     * @return A formatted date string (MMM d yyyy) or the original end date string if parsing failed.
     */
    public String getToString() {
        if (endDate != null) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return to;
    }

    /**
     * Returns the parsed LocalDate object for the start date, or null if parsing failed.
     *
     * @return The LocalDate representation of the start date, or null if parsing failed.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the parsed LocalDate object for the end date, or null if parsing failed.
     *
     * @return The LocalDate representation of the end date, or null if parsing failed.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns the original start date string.
     *
     * @return The original start date string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the original end date string.
     *
     * @return The original end date string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string in the format "[E][status] description (from: startDate to: endDate)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}