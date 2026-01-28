package snaddy.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import snaddy.task.Deadline;
import snaddy.task.Event;
import snaddy.task.Task;
import snaddy.task.ToDo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void findTasksOnDate_emptyList_returnsEmptyList() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        TaskList result = taskList.findTasksOnDate(date);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_deadlineOnExactDate_returnsDeadline() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Deadline deadline = new Deadline("Submit assignment", "2024-01-15");
        taskList.add(deadline);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(1, result.size());
        assertEquals(deadline, result.get(0));
    }

    @Test
    public void findTasksOnDate_deadlineOnDifferentDate_returnsEmptyList() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Deadline deadline = new Deadline("Submit assignment", "2024-01-20");
        taskList.add(deadline);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_eventOnStartDate_returnsEvent() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(1, result.size());
        assertEquals(event, result.get(0));
    }

    @Test
    public void findTasksOnDate_eventOnEndDate_returnsEvent() {
        LocalDate targetDate = LocalDate.of(2024, 1, 17);
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(1, result.size());
        assertEquals(event, result.get(0));
    }

    @Test
    public void findTasksOnDate_eventBetweenStartAndEndDate_returnsEvent() {
        LocalDate targetDate = LocalDate.of(2024, 1, 16);
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(1, result.size());
        assertEquals(event, result.get(0));
    }

    @Test
    public void findTasksOnDate_eventBeforeStartDate_returnsEmptyList() {
        LocalDate targetDate = LocalDate.of(2024, 1, 14);
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_eventAfterEndDate_returnsEmptyList() {
        LocalDate targetDate = LocalDate.of(2024, 1, 18);
        Event event = new Event("Conference", "2024-01-15", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_todoTask_ignored() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        ToDo todo = new ToDo("Buy groceries");
        taskList.add(todo);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_multipleTasksOnSameDate_returnsAllMatching() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Deadline deadline1 = new Deadline("Submit assignment", "2024-01-15");
        Deadline deadline2 = new Deadline("Pay bills", "2024-01-15");
        Event event = new Event("Meeting", "2024-01-15", "2024-01-15");
        ToDo todo = new ToDo("Buy groceries");

        taskList.add(deadline1);
        taskList.add(deadline2);
        taskList.add(event);
        taskList.add(todo);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(3, result.size());
        assertTrue(result.getTasks().contains(deadline1));
        assertTrue(result.getTasks().contains(deadline2));
        assertTrue(result.getTasks().contains(event));
    }

    @Test
    public void findTasksOnDate_deadlineWithInvalidDate_ignored() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Deadline deadline = new Deadline("Submit assignment", "invalid-date");
        taskList.add(deadline);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_eventWithInvalidDate_ignored() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Event event = new Event("Conference", "invalid-date", "2024-01-17");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksOnDate_eventWithInvalidEndDate_ignored() {
        LocalDate targetDate = LocalDate.of(2024, 1, 15);
        Event event = new Event("Conference", "2024-01-15", "invalid-date");
        taskList.add(event);

        TaskList result = taskList.findTasksOnDate(targetDate);
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksByKeyword_emptyList_returnsEmptyList() {
        TaskList result = taskList.findTasksByKeyword("book");
        assertEquals(0, result.size());
    }

    @Test
    public void findTasksByKeyword_matchesDescription_caseInsensitive() {
        Task todo = new ToDo("read book");
        Task deadline = new Deadline("return Book", "2024-01-15");
        Task other = new ToDo("buy groceries");

        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(other);

        TaskList result = taskList.findTasksByKeyword("bOoK");
        assertEquals(2, result.size());
        assertTrue(result.getTasks().contains(todo));
        assertTrue(result.getTasks().contains(deadline));
    }
}
