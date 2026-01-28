package snaddy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void deadline_withValidDate_parsesCorrectly() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals("2024-01-15", deadline.getBy());
        assertEquals(LocalDate.of(2024, 1, 15), deadline.getDate());
        assertFalse(deadline.isDone());
    }

    @Test
    public void deadline_withInvalidDate_returnsNullDate() {
        Deadline deadline = new Deadline("Submit assignment", "invalid-date");
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals("invalid-date", deadline.getBy());
        assertNull(deadline.getDate());
    }

    @Test
    public void getDateString_withValidDate_returnsFormattedDate() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        String dateString = deadline.getDateString();
        assertEquals("Jan 15 2024", dateString);
    }

    @Test
    public void getDateString_withInvalidDate_returnsOriginalString() {
        Deadline deadline = new Deadline("Submit assignment", "invalid-date");
        String dateString = deadline.getDateString();
        assertEquals("invalid-date", dateString);
    }

    @Test
    public void getDateString_withDifferentDateFormats_handlesCorrectly() {
        Deadline deadline1 = new Deadline("Task 1", "2024-12-25");
        assertEquals("Dec 25 2024", deadline1.getDateString());

        Deadline deadline2 = new Deadline("Task 2", "2023-06-01");
        assertEquals("Jun 1 2023", deadline2.getDateString());
    }

    @Test
    public void toString_withValidDate_includesFormattedDate() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        String result = deadline.toString();
        assertTrue(result.contains("[D]"));
        assertTrue(result.contains("Submit assignment"));
        assertTrue(result.contains("Jan 15 2024"));
        assertTrue(result.contains("(by:"));
    }

    @Test
    public void toString_withInvalidDate_includesOriginalString() {
        Deadline deadline = new Deadline("Submit assignment", "invalid-date");
        String result = deadline.toString();
        assertTrue(result.contains("[D]"));
        assertTrue(result.contains("Submit assignment"));
        assertTrue(result.contains("invalid-date"));
    }

    @Test
    public void toString_whenMarked_showsMarkedStatus() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        deadline.markAsDone();
        String result = deadline.toString();
        assertTrue(result.contains("[X]"));
    }

    @Test
    public void toString_whenUnmarked_showsUnmarkedStatus() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        deadline.markAsDone();
        deadline.markAsNotDone();
        String result = deadline.toString();
        assertTrue(result.contains("[ ]"));
    }

    @Test
    public void markAsDone_changesStatus() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        assertFalse(deadline.isDone());
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    @Test
    public void markAsNotDone_changesStatus() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        deadline.markAsDone();
        assertTrue(deadline.isDone());
        deadline.markAsNotDone();
        assertFalse(deadline.isDone());
    }

    @Test
    public void getStatusIcon_whenDone_returnsX() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void getStatusIcon_whenNotDone_returnsSpace() {
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        assertEquals(" ", deadline.getStatusIcon());
    }
}
