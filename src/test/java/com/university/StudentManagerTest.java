package com.university;

import com.university.generators.Evaluation;
import com.university.generators.Student;
import com.university.managers.EvaluationManager;
import com.university.managers.StudentManager;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {

    @Test
    public void testAddStudent() {
        StudentManager manager = new StudentManager();
        manager.addStudent("Alice", "Mathematics");

        TreeMap<String, Student> students = manager.getStudentMap();
        assertEquals(1, students.size());

        Student student = students.get("Alice");
        assertNotNull(student);
        assertEquals("Alice", student.getName());
        assertEquals(1, student.Get_Course_Count());
        assertTrue(student.has_Already_Course("Mathematics"));
    }

    @Test
    public void testAddStudentExistingStudentDifferentCourse() {
        StudentManager manager = new StudentManager();
        manager.addStudent("Alice", "Mathematics");
        manager.addStudent("Alice", "Physics");

        TreeMap<String, Student> students = manager.getStudentMap();
        Student student = students.get("Alice");

        assertEquals(2, student.Get_Course_Count());
        assertTrue(student.has_Already_Course("Mathematics"));
        assertTrue(student.has_Already_Course("Physics"));
    }

    @Test
    public void testAddEvaluation() {
        EvaluationManager manager = new EvaluationManager();
        manager.addEvaluation("Mathematics", "Exam 1", "Alice", "Exercise 1", 8.0, "Test");

        Map<String, Evaluation> evaluations = manager.getEvaluations();
        String key = "Mathematics-Exam 1-Alice-Test";
        Evaluation evaluation = evaluations.get(key);

        assertNotNull(evaluation);
        assertEquals("Mathematics", evaluation.getSubjectName());
        assertEquals("Exam 1", evaluation.getEvaluationName());
        assertEquals("Test", evaluation.Get_Evaluation_Type());
        assertEquals(1, evaluation.Get_Grades().size());
        assertEquals(8.0, evaluation.Get_Grades().get("Exercise 1"));
    }

    @Test
    public void testAddGradeToExistingEvaluation() {
        EvaluationManager manager = new EvaluationManager();
        manager.addEvaluation("Physics", "Final", "Bob", "Problem 1", 9.0, "Exam");
        manager.addEvaluation("Physics", "Final", "Bob", "Problem 2", 7.5, "Exam");

        Map<String, Evaluation> evaluations = manager.getEvaluations();
        String key = "Physics-Final-Bob-Exam";
        Evaluation evaluation = evaluations.get(key);

        assertNotNull(evaluation);
        assertEquals(2, evaluation.Get_Grades().size());
        assertEquals(9.0, evaluation.Get_Grades().get("Problem 1"));
        assertEquals(7.5, evaluation.Get_Grades().get("Problem 2"));
    }
}

