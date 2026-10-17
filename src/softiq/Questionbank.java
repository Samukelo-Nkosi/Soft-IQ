/**
 * Central rep of our quiz topics and their
 * questions, also .. this is the section where
 * we can add new topics/questions here to extend the quiz
 */


package softiq;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Questionbank {
    private static final Map<String, List<Question>> BANK = new LinkedHashMap<>();

    static {
        BANK.put("Java", buildJavaQuestions());
        BANK.put("Networking", buildNetworkingQuestions());
        BANK.put("OOP Concepts", buildOopQuestions());
        BANK.put("Databases & SQL", buildDatabaseQuestions());
    }

    public static String[] getTopics() {
        return BANK.keySet().toArray(new String[0]);
    }

    public static List<Question> getQuestions(String topic) {
        return BANK.getOrDefault(topic, new ArrayList<>());
    }

    private static List<Question> buildJavaQuestions() {
        List<Question> q = new ArrayList<>();
        q.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"}, 1));
        q.add(new Question("Which of these is NOT a primitive data type in Java?",
                new String[]{"int", "boolean", "String", "char"}, 2));
        q.add(new Question("What does the JVM stand for?",
                new String[]{"Java Verified Machine", "Java Virtual Machine", "Java Variable Method", "Java Version Manager"}, 1));
        q.add(new Question("Which collection class does NOT allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Stack"}, 2));
        q.add(new Question("What is the default value of a boolean instance variable in Java?",
                new String[]{"true", "false", "0", "null"}, 1));
        q.add(new Question("Which keyword prevents a class from being subclassed?",
                new String[]{"static", "final", "private", "abstract"}, 1));
        q.add(new Question("Which of these is used for exception handling in Java?",
                new String[]{"try-catch", "if-else", "switch-case", "for-loop"}, 0));
        q.add(new Question("What is the entry point method of a Java application?",
                new String[]{"start()", "run()", "main()", "init()"}, 2));
        return q;
    }

    private static List<Question> buildNetworkingQuestions() {
        List<Question> q = new ArrayList<>();
        q.add(new Question("Which layer of the OSI model is responsible for routing?",
                new String[]{"Data Link", "Network", "Transport", "Session"}, 1));
        q.add(new Question("What does TCP stand for?",
                new String[]{"Transfer Control Protocol", "Transmission Control Protocol", "Total Control Protocol", "Transport Communication Protocol"}, 1));
        q.add(new Question("Which port does HTTPS use by default?",
                new String[]{"21", "80", "443", "8080"}, 2));
        q.add(new Question("Which protocol is connectionless?",
                new String[]{"TCP", "UDP", "FTP", "HTTP"}, 1));
        q.add(new Question("What does DNS primarily do?",
                new String[]{"Encrypts traffic", "Assigns IP addresses", "Resolves domain names to IP addresses", "Routes packets"}, 2));
        q.add(new Question("Which device operates mainly at the Data Link layer?",
                new String[]{"Router", "Switch", "Hub", "Firewall"}, 1));
        q.add(new Question("What is the purpose of DHCP?",
                new String[]{"Automatically assign IP addresses", "Encrypt network traffic", "Translate domain names", "Filter packets"}, 0));
        q.add(new Question("Which of these is a private IP address range?",
                new String[]{"8.8.8.8", "192.168.1.1", "1.1.1.1", "172.217.0.0"}, 1));
        return q;
    }

    private static List<Question> buildOopQuestions() {
        List<Question> q = new ArrayList<>();
        q.add(new Question("Which OOP principle allows one interface to be used for a general class of actions?",
                new String[]{"Encapsulation", "Polymorphism", "Inheritance", "Abstraction"}, 1));
        q.add(new Question("What is encapsulation?",
                new String[]{"Hiding internal state and requiring interaction through methods", "Creating multiple classes", "Inheriting from a parent class", "Overloading methods"}, 0));
        q.add(new Question("Which concept allows a subclass to provide a specific implementation of a method already defined in its superclass?",
                new String[]{"Overloading", "Overriding", "Encapsulation", "Composition"}, 1));
        q.add(new Question("An abstract class in most OOP languages...",
                new String[]{"Can be instantiated directly", "Cannot be instantiated directly", "Must have no methods", "Cannot have constructors"}, 1));
        q.add(new Question("What is method overloading?",
                new String[]{"Same method name with different parameters", "Same method name and same parameters in a subclass", "Multiple classes with the same name", "Calling a method recursively"}, 0));
        q.add(new Question("Which relationship best describes 'a Car has an Engine'?",
                new String[]{"Inheritance", "Composition", "Polymorphism", "Interface"}, 1));
        q.add(new Question("What does 'abstraction' focus on?",
                new String[]{"Exposing implementation details", "Hiding complex implementation and showing only essentials", "Combining data and methods", "Creating multiple objects"}, 1));
        return q;
    }

    private static List<Question> buildDatabaseQuestions() {
        List<Question> q = new ArrayList<>();
        q.add(new Question("Which SQL statement is used to retrieve data?",
                new String[]{"GET", "SELECT", "FETCH", "SHOW"}, 1));
        q.add(new Question("Which SQL clause is used to filter rows before grouping?",
                new String[]{"HAVING", "WHERE", "GROUP BY", "ORDER BY"}, 1));
        q.add(new Question("Which key uniquely identifies a record in a table?",
                new String[]{"Foreign key", "Candidate key", "Primary key", "Composite key"}, 2));
        q.add(new Question("What does the acronym ACID stand for in database transactions?",
                new String[]{"Atomicity, Consistency, Isolation, Durability", "Accuracy, Consistency, Integrity, Durability", "Atomicity, Concurrency, Isolation, Durability", "Availability, Consistency, Isolation, Durability"}, 0));
        q.add(new Question("Which type of SQL JOIN returns only matching rows from both tables?",
                new String[]{"LEFT JOIN", "RIGHT JOIN", "INNER JOIN", "FULL OUTER JOIN"}, 2));
        q.add(new Question("Which normal form removes partial dependency on a composite primary key?",
                new String[]{"1NF", "2NF", "3NF", "BCNF"}, 1));
        q.add(new Question("Which SQL keyword is used to remove duplicate rows from a result set?",
                new String[]{"UNIQUE", "DISTINCT", "REMOVE", "FILTER"}, 1));
        return q;
    }
}
