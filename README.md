# SoftIQ

> **Test your ICT soft skills knowledge.**

SoftIQ is a Java Swing desktop quiz application designed to help students and developers test their knowledge of core software development concepts through interactive multiple-choice quizzes.

The application provides topic-based quizzes, immediate answer feedback, live scoring, progress tracking, and a final grade after each quiz.

---

## Features

- **Modern desktop interface** built with Java Swing
- **Welcome screen** with application description and navigation
- **Topic selection** with question counts
- **Multiple-choice quizzes** with one question displayed at a time
- **Instant feedback** after submitting an answer
- **Live score and progress tracking**
- **Correct-answer explanations** when an answer is incorrect
- **Quiz completion screen** showing:
    - Final score
    - Percentage
    - Letter grade
    - Performance remark
- **Retake functionality**
- **Switch between topics** without restarting the application
- **Persistent quiz history** stored locally in SQLite, with per-player filtering
- **Clear-history control** for deleting saved attempts
- **Exit confirmation** to prevent accidental application closure
- **Expandable question bank** for adding new subjects and questions

---

## ICT Project Submission

### Project Description

**SoftIQ** is a Java desktop application that helps students practise and review foundational ICT concepts through short, topic-based quizzes. It currently covers Java and object-oriented programming, networking, PHP, HTML, CSS, Python, and databases and SQL. Players choose a topic, answer multiple-choice questions, receive immediate feedback, and see a score, percentage, grade, and performance remark. Quiz attempts are saved locally in SQLite, allowing players to review their history and average results.

### Innovation, Impact, and Practical Application

SoftIQ brings several introductory ICT subjects together in one lightweight learning tool. Its immediate explanations help learners identify knowledge gaps while practising, and its saved results make it easier to revisit progress over time. Students can use it for independent revision, quick knowledge checks, or peer study sessions. Because the quiz and history run locally on the computer, the current version does not require an online account or hosted service.

The project is an educational prototype: its question bank is fixed in the source code, and the saved history is local to one computer. Results are intended for practice and self-review, not as a formal assessment or a substitute for instruction.

### 10-Minute Demonstration Video Outline

| Time | Demonstration |
|---|---|
| 0:00–1:00 | Introduce SoftIQ and the learning problem it addresses: practising foundational ICT topics with useful feedback. |
| 1:00–2:00 | Launch the application, point out the live date and time, and explain how to start a quiz. |
| 2:00–3:00 | Enter a player name and show the available topics and question counts. |
| 3:00–5:30 | Start a quiz, select and submit answers, and demonstrate immediate correct/incorrect feedback and answer explanations. |
| 5:30–6:30 | Finish the quiz and explain the score, percentage, grade, and performance remark. |
| 6:30–8:00 | Open quiz history; show saved attempts, the player filter, and the average score. |
| 8:00–9:00 | Demonstrate retaking the topic or choosing another topic, then explain how the question bank can be extended. |
| 9:00–10:00 | Summarize the project's practical value, current local-storage limitation, and possible future improvements. |

Before recording, use a clean or clearly labelled test history, confirm the application launches, and prepare one quiz so the demonstration fits the time limit. Avoid showing private information in the video.

---

## Available Topics

SoftIQ currently includes seven topics:

| Topic | Questions |
|---|---:|
| Java (including OOP) | 15 |
| Networking | 8 |
| PHP | 8 |
| HTML | 8 |
| CSS | 8 |
| Python | 8 |
| Databases & SQL | 7 |
| **Total** | **62** |

### Java
Covers fundamental Java concepts including:

- Inheritance
- Primitive and reference data types
- JVM
- Collections
- Default values
- `final`
- Exception handling
- The `main()` method
- Object-oriented programming: encapsulation, inheritance, polymorphism, abstraction, overriding, overloading, and composition

### Networking
Covers fundamental networking concepts including:

- OSI model
- TCP and UDP
- HTTPS
- DNS
- Switches
- DHCP
- Private IP addresses

### PHP
Covers PHP tags, variables, output, comparisons, form data, functions, file extensions, and null values.

### HTML
Covers document structure, headings, links, attributes, images, alternative text, semantic elements, and HTML5 declarations.

### CSS
Covers selectors, colors, the box model, spacing, Flexbox, and comments.

### Python
Covers functions, comments, output, collections, `None`, conditionals, exponentiation, and source-file extensions.

### Databases & SQL
Covers:

- `SELECT`
- `WHERE`
- Primary keys
- ACID transactions
- SQL joins
- Database normalization
- `DISTINCT`

---

## Grading System

SoftIQ uses the following grading scale:

| Percentage | Grade |
|:---:|:---:|
| 90–100% | **A** |
| 80–89% | **B** |
| 70–79% | **C** |
| 60–69% | **D** |
| Below 60% | **F** |

The results screen also provides a short performance remark based on the final grade.

---

## Application Flow

```text
┌─────────────────┐
│   Welcome Page  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Topic Selection│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Quiz Screen   │
│                 │
│ Question        │
│      ↓          │
│ Select Answer   │
│      ↓          │
│ Submit          │
│      ↓          │
│ Feedback        │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Results Screen  │
│                 │
│ Score / % /     │
│ Letter Grade    │
└─────────────────┘
```

---

## Project Structure

```text
SoftIQ/
│
├── src/
│   └── softiq/
│       ├── SoftIQ.java
│       ├── MainFrame.java
│       ├── WelcomePanel.java
│       ├── TopicPanel.java
│       ├── QuizPanel.java
│       ├── ResultPanel.java
│       ├── HistoryPanel.java
│       ├── QuizRepository.java
│       ├── DatabaseManager.java
│       ├── Grading.java
│       ├── Question.java
│       └── Questionbank.java
│
├── pom.xml
│
└── README.md
```

### Class Responsibilities

| Class           | Responsibility |
|-----------------|---|
| `SoftIQ.java`   | Application entry point |
| `MainFrame.java` | Main window and screen navigation |
| `WelcomePanel.java` | Landing page and application controls |
| `TopicPanel.java` | Displays available quiz topics |
| `QuizPanel.java` | Handles questions, answers, feedback, progress, and scoring |
| `ResultPanel.java` | Displays the final score, percentage, and grade |
| `HistoryPanel.java` | Displays and filters saved quiz attempts |
| `QuizRepository.java` | Reads and writes quiz attempts |
| `DatabaseManager.java` | Creates and opens the local SQLite database |
| `Question.java` | Data model representing an individual question |
| `Questionbank.java` | Stores topics and their questions |

---

## Technologies Used

### Programming Language

- **Java**

### GUI Framework

- **Java Swing**
- `JFrame`
- `JPanel`
- `JButton`
- `JLabel`
- `JRadioButton`
- `ButtonGroup`
- `CardLayout`
- `GridBagLayout`
- `GridLayout`

### Development Tools

- Visual Studio Code
- IntelliJ IDEA
- Java Development Kit (JDK)
- Git / GitHub

---

## Requirements

To run SoftIQ, you need:

- **JDK 8 or later**
- **Apache Maven 3.6 or later**
- A Java-compatible IDE or terminal
- Windows, macOS, or Linux

The project is compatible with modern Java versions such as **JDK 21**.

Verify your Java installation:

```bash
java -version
javac -version
```

---

## Running the Application

### Option 1: Command Line

Open a terminal in the `SoftIQ` project directory and run:

```bash
mvn clean compile exec:java
```

Maven downloads the SQLite JDBC driver and other required build plugins. Quiz history is saved to:

```text
<user-home>/.softiq/softiq.db
```

---

### Option 2: Visual Studio Code

1. Install the **Extension Pack for Java** from Microsoft.
2. Open the `SoftIQ` folder in VS Code.
3. Open:

```text
src/Main.java
```

4. Click **Run** above the `main()` method to launch the quiz GUI. `Main.java` delegates to `softiq.SoftIQ`, the application entry point.

Alternatively, press:

```text
F5
```

The included `.vscode/launch.json` configuration is already configured to launch:

```text
softiq.SoftIQ
```

---

### Option 3: IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **Open**.
3. Choose the `SoftIQ` project folder.
4. Configure a JDK if IntelliJ asks you to.
5. Mark `src` as a **Sources Root** if it is not detected automatically.
6. Open:

```text
src/softiq/SoftIQ.java
```

7. Run the `main()` method.

---

## How the Quiz Works

1. Launch SoftIQ.
2. Select **Start Quiz**.
3. Choose a topic.
4. Read the question.
5. Select one of the available answers.
6. Click **Submit Answer**.
7. SoftIQ immediately indicates whether the answer is correct.
8. If the answer is incorrect, the correct answer is displayed.
9. Continue through the remaining questions.
10. After the final question, select **Finish Quiz**.
11. View your score, percentage, and grade.
12. Retake the topic or choose another topic.

---

## Adding Questions

Questions are managed centrally inside:

```text
src/softiq/Questionbank.java
```

A question follows this structure:

```java
q.add(new Question(
    "Your question here?",
    new String[]{
        "Option A",
        "Option B",
        "Option C",
        "Option D"
    };
    1
));
```

The final number represents the index of the correct answer.

Java arrays use **zero-based indexing**, so:

```text
0 = Option A
1 = Option B
2 = Option C
3 = Option D
```

For example:

```java
q.add(new Question(
    "Which language is used to build SoftIQ?",
    new String[]{"Python", "Java", "C++", "PHP"},
    1
));
```

In this example, `1` means **Java** is the correct answer.

---

## Adding a New Topic

New topics can also be added through `Questionbank.java`.

First, register the topic:

```java
BANK.put("Operating Systems", buildOperatingSystemQuestions());
```

Then create its question list:

```java
private static List<Question> buildOperatingSystemQuestions() {
    List<Question> q = new ArrayList<>();

    q.add(new Question(
        "What does CPU stand for?",
        new String[]{
            "Central Processing Unit",
            "Computer Processing Utility",
            "Central Program Unit",
            "Control Processing Unit"
        },
        0
    ));

    return q;
}
```

The new topic will automatically appear on the topic-selection screen because the UI reads topics directly from `Questionbank`.

---

## Architecture

SoftIQ follows a simple component-based structure.

```text
                    SoftIQ.java
                         │
                         ▼
                    MainFrame
                         │
             ┌───────────┼───────────┐
             ▼           ▼           ▼
       WelcomePanel  TopicPanel  QuizPanel
                                     │
                                     ▼
                               Questionbank
                                     │
                                     ▼
                                  Question
                                     │
                                     ▼
                               ResultPanel
```

`MainFrame` controls navigation between the application's panels using Java Swing's `CardLayout`.

---

## Design Decisions

### CardLayout Navigation

`MainFrame` uses `CardLayout` to switch between:

- Welcome
- Topic selection
- Quiz
- Results

This keeps the application inside a single main window while allowing different screens to behave independently.

### Centralized Question Bank

All quiz content is stored in `Questionbank.java`. This makes it easier to:

- Add questions
- Add new topics
- Modify existing questions
- Maintain quiz content without changing the UI

### Question Data Model

The `Question` class keeps the question text, answer options, and correct answer index together.

This separates quiz data from the interface and makes the application easier to maintain.

---

## Future Improvements

Possible future versions of SoftIQ could include:

- [ ] Larger question bank
- [ ] Randomized questions
- [ ] Randomized answer options
- [ ] Difficulty levels
- [ ] Timed quizzes
- [ ] High-score leaderboard
- [ ] User profiles
- [ ] Custom quiz creation
- [ ] Dark/light theme switching
- [ ] Sound effects
- [ ] Progress statistics
- [ ] Exportable results
- [ ] Online question database
- [ ] Unit and integration tests

---

## Known Limitations

The current version is intentionally lightweight:

- Quiz questions are stored directly in Java source code.
- There is no user authentication.
- Quiz history is stored in a local SQLite database and is not synchronized between devices.
- The question order is fixed.
- Each topic contains a relatively small number of questions.

These limitations provide opportunities for future development.

---

## Contributing

Contributions and improvements are welcome.

A typical workflow is:

```bash
git clone <repository-url>
cd SoftIQ
```

Create a feature branch:

```bash
git checkout -b feature/new-topic
```

Make your changes, test the application, then commit:

```bash
git add .
git commit -m "Add new quiz topic"
```

Push the branch:

```bash
git push origin feature/new-topic
```

Then open a pull request.

---

## Testing Checklist

Before committing changes, verify that:

- [ ] The project compiles without errors.
- [ ] The application launches successfully.
- [ ] All topics appear correctly.
- [ ] Questions display correctly.
- [ ] Answers can be selected.
- [ ] Correct answers increase the score.
- [ ] Incorrect answers display the correct answer.
- [ ] Progress updates correctly.
- [ ] The final score is calculated correctly.
- [ ] The grade matches the percentage.
- [ ] Retaking a quiz resets the score.
- [ ] Returning to topics works correctly.
- [ ] The application exits correctly.

---

## License

This project is intended primarily as an educational and portfolio project.

If you plan to distribute or reuse the project, add an appropriate open-source license such as MIT, Apache-2.0, or GPL-3.0.

---

## Author

**Samukelo Nkosi**

Application Development / SoftIQ

---

## Project Status

**Status:** Active Development

SoftIQ is a Java desktop application that can be expanded from a simple educational quiz into a larger learning and assessment platform.

---

> **SoftIQ - Learn. Test. Improve.**
