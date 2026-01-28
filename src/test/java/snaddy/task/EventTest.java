package snaddy.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void event_withValidDates_parsesCorrectly() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        assertEquals("Conference", event.getDescription());
        assertEquals("2024-01-15", event.getFrom());
        assertEquals("2024-01-17", event.getTo());
        assertEquals(LocalDate.of(2024, 1, 15), event.getStartDate());
        assertEquals(LocalDate.of(2024, 1, 17), event.getEndDate());
        assertFalse(event.isDone());
    }

    @Test
    public void event_withInvalidStartDate_returnsNullStartDate() {
        Event event = new Event("Conference", "invalid-date", "2024-01-17");
        assertEquals("Conference", event.getDescription());
        assertEquals("invalid-date", event.getFrom());
        assertEquals("2024-01-17", event.getTo());
        assertNull(event.getStartDate());
        assertEquals(LocalDate.of(2024, 1, 17), event.getEndDate());
    }

    @Test
    public void event_withInvalidEndDate_returnsNullEndDate() {
        Event event = new Event("Conference", "2024-01-15", "invalid-date");
        assertEquals("Conference", event.getDescription());
        assertEquals("2024-01-15", event.getFrom());
        assertEquals("invalid-date", event.getTo());
        assertEquals(LocalDate.of(2024, 1, 15), event.getStartDate());
        assertNull(event.getEndDate());
    }

    @Test
    public void event_withBothInvalidDates_returnsNullDates() {
        Event event = new Event("Conference", "invalid-start", "invalid-end");
        assertNull(event.getStartDate());
        assertNull(event.getEndDate());
    }

    @Test
    public void getFromString_withValidDate_returnsFormattedDate() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        String fromString = event.getFromString();
        assertEquals("Jan 15 2024", fromString);
    }

    @Test
    public void getFromString_withInvalidDate_returnsOriginalString() {
        Event event = new Event("Conference", "invalid-date", "2024-01-17");
        String fromString = event.getFromString();
        assertEquals("invalid-date", fromString);
    }

    @Test
    public void getToString_withValidDate_returnsFormattedDate() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        String toString = event.getToString();
        assertEquals("Jan 17 2024", toString);
    }

    @Test
    public void getToString_withInvalidDate_returnsOriginalString() {
        Event event = new Event("Conference", "2024-01-15", "invalid-date");
        String toString = event.getToString();
        assertEquals("invalid-date", toString);
    }

    @Test
    public void toString_withValidDates_includesFormattedDates() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        String result = event.toString();
        assertTrue(result.contains("[E]"));
        assertTrue(result.contains("Conference"));
        assertTrue(result.contains("Jan 15 2024"));
        assertTrue(result.contains("Jan 17 2024"));
        assertTrue(result.contains("(from:"));
        assertTrue(result.contains("to:"));
    }

    @Test
    public void toString_withInvalidDates_includesOriginalStrings() {
        Event event = new Event("Conference", "invalid-start", "invalid-end");
        String result = event.toString();
        assertTrue(result.contains("[E]"));
        assertTrue(result.contains("Conference"));
        assertTrue(result.contains("invalid-start"));
        assertTrue(result.contains("invalid-end"));
    }

    @Test
    public void toString_whenMarked_showsMarkedStatus() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        event.markAsDone();
        String result = event.toString();
        assertTrue(result.contains("[X]"));
    }

    @Test
    public void toString_whenUnmarked_showsUnmarkedStatus() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        event.markAsDone();
        event.markAsNotDone();
        String result = event.toString();
        assertTrue(result.contains("[ ]"));
    }

    @Test
    public void markAsDone_changesStatus() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
    }

    @Test
    public void markAsNotDone_changesStatus() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        event.markAsDone();
        assertTrue(event.isDone());
        event.markAsNotDone();
        assertFalse(event.isDone());
    }

    @Test
    public void getStatusIcon_whenDone_returnsX() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        event.markAsDone();
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void getStatusIcon_whenNotDone_returnsSpace() {
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void event_withSameStartAndEndDate_handlesCorrectly() {
        Event event = new Event("One day event", "2024-01-15", "2024-01-15");
        assertEquals(LocalDate.of(2024, 1, 15), event.getStartDate());
        assertEquals(LocalDate.of(2024, 1, 15), event.getEndDate());
        assertEquals("Jan 15 2024", event.getFromString());
        assertEquals("Jan 15 2024", event.getToString());
    }

    @Test
    public void getFromString_withDifferentDateFormats_handlesCorrectly() {
        Event event1 = new Event("Event 1", "2024-12-25", "2024-12-26");
        assertEquals("Dec 25 2024", event1.getFromString());

        Event event2 = new Event("Event 2", "2023-06-01", "2023-06-05");
        assertEquals("Jun 1 2023", event2.getFromString());
    }
}
