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
}