package com.university;

import com.university.FileManagers.FileHandler;
import com.university.Generators.Evaluation;
import com.university.Generators.Student;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @Test
    public void testReadCSV() throws IOException {
        File tempFile = File.createTempFile("testStudents", ".csv");
        tempFile.deleteOnExit();

        String data = "Student_Name,Course_Name\n" +
                "Alice,Mathematics\n" +
                "Bob,Physics\n";

        java.nio.file.Files.write(tempFile.toPath(), data.getBytes());

        FileHandler fileHandler = new FileHandler();
        List<String[]> result = fileHandler.readCSV(tempFile.getAbsolutePath());

        assertEquals(2, result.size());
        assertArrayEquals(new String[]{"Alice", "Mathematics"}, result.get(0));
        assertArrayEquals(new String[]{"Bob", "Physics"}, result.get(1));
    }

    @Test
    public void testWriteCSV() throws IOException {
        File tempFile = File.createTempFile("testOutputStudents", ".csv");
        tempFile.deleteOnExit();

        TreeMap<String, Student> studentMap = new TreeMap<>();
        Student student1 = new Student("Alice", 2);
        student1.Add_Course("Mathematics");
        student1.Add_Course("Physics");
        studentMap.put("Alice", student1);

        Student student2 = new Student("Bob", 1);
        student2.Add_Course("Chemistry");
        studentMap.put("Bob", student2);

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeCSV(tempFile.getAbsolutePath(), studentMap);

        List<String> lines = java.nio.file.Files.readAllLines(tempFile.toPath());
        assertEquals(3, lines.size());
        assertEquals("Student_Name,Course_Count", lines.get(0));
        assertTrue(lines.contains("Alice,2"));
        assertTrue(lines.contains("Bob,1"));
    }

    @Test
    public void testWriteEvaluationCSV() throws IOException {
        File tempFile = File.createTempFile("testOutputEvaluations", ".csv");
        tempFile.deleteOnExit();

        Map<String, Evaluation> evaluationMap = new HashMap<>();
        Evaluation evaluation1 = new Evaluation("Mathematics", "Exam 1", "Exercise 1", "Test");
        evaluation1.Add_Grade("Exercise 1", 8.5);
        evaluation1.Add_Grade("Exercise 2", 7.0);
        evaluationMap.put("Mathematics-Exam 1-Alice-Test", evaluation1);

        Evaluation evaluation2 = new Evaluation("Physics", "Final Exam", "Problem 1", "Exam");
        evaluation2.Add_Grade("Problem 1", 9.0);
        evaluationMap.put("Physics-Final Exam-Bob-Exam", evaluation2);

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeEvaluationCSV(tempFile.getAbsolutePath(), evaluationMap);

        List<String> lines = java.nio.file.Files.readAllLines(tempFile.toPath());
        assertEquals(3, lines.size());
        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Final_Grade", lines.get(0));
        String mathLine = lines.stream().filter(line -> line.startsWith("Mathematics,Exam 1,Alice")).findFirst().orElse(null);
        assertNotNull(mathLine, "Expected line for Mathematics, Exam 1, Alice not found");
        String[] mathLineParts = mathLine.split(",");
        double finalGrade = Double.parseDouble(mathLineParts[3]);
        assertEquals(7.0, finalGrade, 0.1);
    }
}
