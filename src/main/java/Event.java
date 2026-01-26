import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.startDate = parseDate(from);
        this.endDate = parseDate(to);
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null; // Not a valid date format, treat as string
        }
    }

    public String getFromString() {
        if (startDate != null) {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return from;
    }

    public String getToString() {
        if (endDate != null) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return to;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}