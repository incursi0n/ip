import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = parseDate(by);
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null; // Not a valid date format, treat as string
        }
    }

    public String getDateString() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return by;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString() + ")";
    }
}