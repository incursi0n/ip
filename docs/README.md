# Snaddy User Guide

**Snaddy** is a desktop task manager with a chat-style interface. You type commands in the text box and Snaddy keeps track of your todos, deadlines, and events. If you type quickly, Snaddy can manage your tasks faster than clicking through menus.

---

## Quick start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `snaddy.jar` from the [releases](https://github.com/incursi0n/ip/releases) page (or build from source with `./gradlew shadowJar`).
3. Double-click the JAR or run `java -jar snaddy.jar` in a terminal.
4. When the window opens, type a command in the box at the bottom and press **Enter** (or click **Send**).
5. Try: **`list`** to see your tasks, **`todo read book`** to add a task, or **`bye`** to exit.

---

## Features

**:information_source: Notes about the command format:**

* Words in `UPPER_CASE` are the parameters you supply.  
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is the text of your task.
* `INDEX` is the **1-based** position of a task in the current list (1 = first task, 2 = second, …).
* Dates must be in **`yyyy-mm-dd`** format (e.g. `2025-12-25`).
* For **event**, use **`/from`** before **`/to`**; the start date must be earlier than the end date.
* Leading and trailing spaces in commands are ignored.

---

### Listing all tasks: `list`

Shows all tasks (todos, deadlines, events) in your list.

**Format:** `list`

**Example:** `list`

---

### Adding a todo: `todo`

Adds a task with no date.

**Format:** `todo DESCRIPTION`

**Examples:**
* `todo buy groceries`
* `todo return library book`

---

### Adding a deadline: `deadline`

Adds a task with a due date.

**Format:** `deadline DESCRIPTION /by yyyy-mm-dd`

**Examples:**
* `deadline submit report /by 2025-03-15`
* `deadline pay bills /by 2025-02-28`

---

### Adding an event: `event`

Adds a task that spans a start and end date.

**Format:** `event DESCRIPTION /from yyyy-mm-dd /to yyyy-mm-dd`

* The start date (`/from`) must be **before** the end date (`/to`).
* Use **`/from`** first, then **`/to`**.

**Examples:**
* `event team meeting /from 2025-02-10 /to 2025-02-10`
* `event conference /from 2025-03-01 /to 2025-03-03`

---

### Marking a task as done: `mark`

Marks the task at the given index as completed.

**Format:** `mark INDEX`

**Example:** `mark 1` — marks the first task in the list as done.

---

### Marking a task as not done: `unmark`

Marks the task at the given index as not completed.

**Format:** `unmark INDEX`

**Example:** `unmark 2` — marks the second task as not done.

---

### Deleting a task: `delete`

Removes the task at the given index from the list.

**Format:** `delete INDEX`

**Examples:**
* `list` then `delete 3` — deletes the 3rd task in the list.
* `find book` then `delete 1` — deletes the 1st task in the find results.

---

### Viewing tasks on a date: `on`

Shows only tasks that fall on the given date (deadlines due on that day, or events that include that day).

**Format:** `on yyyy-mm-dd`

**Example:** `on 2025-02-10`

---

### Finding tasks by keyword: `find`

Shows tasks whose description contains the keyword (case-insensitive).

**Format:** `find KEYWORD`

**Examples:**
* `find book` — lists tasks with "book" in the description.
* `find meeting`

---

### Exiting the program: `bye`

Closes Snaddy.

**Format:** `bye`

---

### Saving data

Task data is saved automatically to `data/snaddy.txt` after any change. You do not need to save manually.

---

## Command summary

| Action        | Format | Example |
|---------------|--------|---------|
| **List**      | `list` | `list` |
| **Todo**      | `todo DESCRIPTION` | `todo buy milk` |
| **Deadline**  | `deadline DESCRIPTION /by yyyy-mm-dd` | `deadline report /by 2025-03-01` |
| **Event**     | `event DESCRIPTION /from yyyy-mm-dd /to yyyy-mm-dd` | `event trip /from 2025-04-01 /to 2025-04-05` |
| **Mark**      | `mark INDEX` | `mark 1` |
| **Unmark**    | `unmark INDEX` | `unmark 1` |
| **Delete**    | `delete INDEX` | `delete 2` |
| **On date**   | `on yyyy-mm-dd` | `on 2025-02-10` |
| **Find**      | `find KEYWORD` | `find book` |
| **Exit**      | `bye` | `bye` |
