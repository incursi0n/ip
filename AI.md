# AI Tool Usage Log

<<<<<<< HEAD
This document tracks the use of AI tools (Cursor AI assistant) in this project.
=======
This document tracks the use of AI tools ChatGPT in this project.
>>>>>>> branch-A-BetterGui

## Overview

AI assistance was primarily used for code quality improvements, refactoring, and implementing new features. The AI tool helped significantly with routine tasks like ensuring naming conventions, extracting magic strings, and maintaining code consistency.

## Increments and AI Usage

### A-CodeQuality
<<<<<<< HEAD
**Tool:** Cursor AI Assistant
=======
**Tool:** ChatGPT
>>>>>>> branch-A-BetterGui

**Tasks:**
- Renamed single-letter variable `c` to `command` for better readability
- Replaced `size() == 0` with `isEmpty()` for idiomatic Java
- Extracted magic strings and numbers to named constants in `Parser.java` and `Storage.java`
- Replaced manual resource closing with try-with-resources in `Storage.java`
- Fixed comment formatting issues

**Observations:**
- **What worked well:** AI was excellent at identifying code quality issues and applying Java coding standards consistently. It caught naming violations (single-letter variables), suggested better patterns (isEmpty vs size comparison), and helped extract magic values systematically.
- **Time saved:** ~30-45 minutes on manual refactoring and checking against coding standards
- **Quality:** All changes passed Checkstyle and maintained existing functionality

### Help Command Feature
<<<<<<< HEAD
**Tool:** Cursor AI Assistant
=======
**Tool:** ChatGPT
>>>>>>> branch-A-BetterGui

**Tasks:**
- Created `HelpCommand.java` following existing command pattern
- Added `showHelp()` method in `Ui.java` with comprehensive command documentation
- Registered help command in `Parser.java`
- Implemented sample data loading on first run

**Observations:**
- **What worked well:** AI quickly generated boilerplate code following existing patterns. The help page formatting was consistent with other UI methods.
- **Time saved:** ~20 minutes on writing help text and command structure
- **Note:** Had to fix one line length violation (121 chars) that Checkstyle caught

### Merge Conflict Resolution
<<<<<<< HEAD
**Tool:** Cursor AI Assistant
=======
**Tool:** ChatGPT
>>>>>>> branch-A-BetterGui

**Tasks:**
- Resolved merge conflicts in `Ui.java` (showTasksOnDate, showMatchingTasks)
- Resolved merge conflict in `Snaddy.java` (run method)
- Ensured consistent return types and proper use of isEmpty()

**Observations:**
- **What worked well:** AI understood the intent of both branches and merged them correctly, keeping the better design (String return types) while applying code quality improvements.
- **Time saved:** ~15 minutes on manual conflict resolution

### Assert Statements
<<<<<<< HEAD
**Tool:** Cursor AI Assistant
=======
**Tool:** ChatGPT
>>>>>>> branch-A-BetterGui

**Tasks:**
- Added assert statements throughout codebase to document assumptions
- Enabled assertions in `build.gradle` for run configuration
- Added assertions for null checks, index bounds, and state invariants

**Observations:**
- **What worked well:** AI systematically added assertions at appropriate points (preconditions, postconditions, invariants) with clear messages.
- **Time saved:** ~25 minutes on identifying where assertions would be valuable

### JAR File Generation
<<<<<<< HEAD
**Tool:** Cursor AI Assistant
=======
**Tool:** ChatGPT
>>>>>>> branch-A-BetterGui

**Tasks:**
- Added `mergeServiceFiles()` to `shadowJar` configuration for JavaFX bundling
- Generated fat JAR with JavaFX libraries included

**Observations:**
- **What worked well:** AI correctly identified the need for `mergeServiceFiles()` when bundling JavaFX and followed the SE-EDU Gradle tutorial.
- **Time saved:** ~10 minutes on researching JavaFX bundling requirements

## Overall Observations

### What Worked Well
- **Code quality improvements:** AI excelled at identifying and fixing naming violations, extracting magic values, and applying coding standards
- **Pattern consistency:** When implementing new features, AI maintained consistency with existing code patterns
- **Refactoring:** Simple refactors like variable renaming and method extraction were handled efficiently
- **Documentation:** AI helped generate clear, consistent Javadoc comments

### What Didn't Work as Well
- **Line length:** Occasionally generated code that exceeded 120-character limit, requiring manual fixes
- **Complex logic:** For complex business logic decisions, manual review was still necessary

### Time Savings
Estimated total time saved: **~100-115 minutes** (1.5-2 hours) across all increments.

### Best Use Cases
- Ensuring variable naming compliance with Java coding standards
- Extracting magic strings/numbers to constants
- Generating boilerplate code following existing patterns
- Identifying code quality issues (Checkstyle violations, best practices)
- Simple refactoring tasks

### Recommendations
- Use AI for routine code quality tasks and refactoring
- Always run Checkstyle and tests after AI-generated changes
- Review AI suggestions for complex logic decisions manually
- Use AI to maintain consistency when adding new features
