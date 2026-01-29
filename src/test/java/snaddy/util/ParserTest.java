package snaddy.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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

public class ParserTest {

    @Test
    public void parse_byeCommand_returnsExitCommand() throws SnaddyException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws SnaddyException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_markCommandWithValidNumber_returnsMarkCommand() throws SnaddyException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_markCommandWithoutNumber_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_markCommandWithInvalidNumber_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void parse_unmarkCommandWithValidNumber_returnsUnmarkCommand() throws SnaddyException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkCommandWithoutNumber_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parse_deleteCommandWithValidNumber_returnsDeleteCommand() throws SnaddyException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteCommandWithoutNumber_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parse_todoCommandWithDescription_returnsAddCommandWithToDo() throws SnaddyException {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof ToDo);
        assertEquals("Buy groceries", addCommand.getTask().getDescription());
    }

    @Test
    public void parse_todoCommandWithoutDescription_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_todoCommandWithEmptyDescription_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void parse_deadlineCommandWithValidFormat_returnsAddCommandWithDeadline() throws SnaddyException {
        Command command = Parser.parse("deadline Submit assignment /by 2024-01-15");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof Deadline);
        Deadline deadline = (Deadline) addCommand.getTask();
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals("2024-01-15", deadline.getBy());
    }

    @Test
    public void parse_deadlineCommandWithoutBy_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("deadline Submit assignment"));
    }

    @Test
    public void parse_deadlineCommandWithoutDescription_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("deadline /by 2024-01-15"));
    }

    @Test
    public void parse_deadlineCommandWithEmptyBy_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("deadline Submit assignment /by"));
    }

    @Test
    public void parse_deadlineCommandWithoutArguments_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void parse_eventCommandWithValidFormat_returnsAddCommandWithEvent() throws SnaddyException {
        Command command = Parser.parse("event Conference /from 2024-01-15 /to 2024-01-17");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.getTask() instanceof Event);
        Event event = (Event) addCommand.getTask();
        assertEquals("Conference", event.getDescription());
        assertEquals("2024-01-15", event.getFrom());
        assertEquals("2024-01-17", event.getTo());
    }

    @Test
    public void parse_eventCommandWithoutFrom_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("event Conference /to 2024-01-17"));
    }

    @Test
    public void parse_eventCommandWithoutTo_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("event Conference /from 2024-01-15"));
    }

    @Test
    public void parse_eventCommandWithoutDescription_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("event /from 2024-01-15 /to 2024-01-17"));
    }

    @Test
    public void parse_eventCommandWithEmptyFrom_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("event Conference /from  /to 2024-01-17"));
    }

    @Test
    public void parse_eventCommandWithEmptyTo_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("event Conference /from 2024-01-15 /to"));
    }

    @Test
    public void parse_onCommandWithValidDate_returnsOnCommand() throws SnaddyException {
        Command command = Parser.parse("on 2024-01-15");
        assertTrue(command instanceof OnCommand);
        OnCommand onCommand = (OnCommand) command;
        assertEquals(LocalDate.of(2024, 1, 15), onCommand.getDate());
    }

    @Test
    public void parse_onCommandWithoutDate_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("on"));
    }

    @Test
    public void parse_onCommandWithInvalidDateFormat_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("on 15-01-2024"));
    }

    @Test
    public void parse_onCommandWithEmptyDate_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("on   "));
    }

    @Test
    public void parse_findCommandWithKeyword_returnsFindCommand() throws SnaddyException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("book", findCommand.getKeyword());
    }

    @Test
    public void parse_findCommandWithoutKeyword_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("find"));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(SnaddyException.class, () -> Parser.parse("unknown"));
    }

    @Test
    public void parse_commandWithMixedCase_worksCorrectly() throws SnaddyException {
        Command command1 = Parser.parse("BYE");
        assertTrue(command1 instanceof ExitCommand);

        Command command2 = Parser.parse("LIST");
        assertTrue(command2 instanceof ListCommand);

        Command command3 = Parser.parse("Mark 1");
        assertTrue(command3 instanceof MarkCommand);
    }

    @Test
    public void parse_deadlineCommandWithWhitespace_handlesCorrectly() throws SnaddyException {
        Command command = Parser.parse("deadline  Submit assignment  /by  2024-01-15  ");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        Deadline deadline = (Deadline) addCommand.getTask();
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals("2024-01-15", deadline.getBy());
    }

    @Test
    public void parse_eventCommandWithWhitespace_handlesCorrectly() throws SnaddyException {
        Command command = Parser.parse("event  Conference  /from  2024-01-15  /to  2024-01-17  ");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        Event event = (Event) addCommand.getTask();
        assertEquals("Conference", event.getDescription());
        assertEquals("2024-01-15", event.getFrom());
        assertEquals("2024-01-17", event.getTo());
    }
}
