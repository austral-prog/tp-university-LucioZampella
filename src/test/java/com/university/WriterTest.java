package com.university;

import com.university.fileManagers.CriteriaWriter;
import com.university.fileManagers.EvaluationWriter;
import com.university.fileManagers.StudentWriter;
import com.university.generators.Criteria;
import com.university.generators.Evaluation;
import com.university.generators.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".csv");
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testCriteriaWriter() throws IOException {
        CriteriaWriter criteriaWriter = new CriteriaWriter();
        Map<String, Criteria> criteriaMap = new HashMap<>();

        Criteria criteria = new Criteria("Mathematics", "Exam", "Percentage", "50.0");
        criteriaMap.put("1", criteria);

        criteriaWriter.writeCSV(tempFile.getAbsolutePath(), criteriaMap);

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            assertEquals("Subject_Name,Evaluation_Name,Criteria_Type,Criteria_Value", reader.readLine());
            assertEquals("Mathematics,Exam,Percentage,50.0", reader.readLine());
        }
    }

    @Test
    void testEvaluationWriter() throws IOException {
        EvaluationWriter evaluationWriter = new EvaluationWriter();
        Map<String, Evaluation> evaluationMap = new HashMap<>();

        Evaluation evaluation = new Evaluation("Physics", "Final Exam", "John Doe", "95.5");
        evaluationMap.put("1", evaluation);

    }

    @Test
    void testStudentWriter() throws IOException {
        StudentWriter studentWriter = new StudentWriter();
        Map<String, Student> studentMap = new HashMap<>();

        Student student = new Student("Alice", 3);
        studentMap.put("1", student);

        studentWriter.writeCSV(tempFile.getAbsolutePath(), studentMap);

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            assertEquals("Student_Name,Course_Count", reader.readLine());
            assertEquals("Alice,3", reader.readLine());
        }
    }
}
