package com.university;

import com.university.generators.Criteria;
import com.university.generators.Evaluation;
import com.university.generators.Student;
import com.university.managers.EvaluationManager;
import com.university.managers.CriteriaManager;
import com.university.managers.StudentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

class UniversityManagerTest {

    private CriteriaManager criteriaManager;
    private EvaluationManager evaluationManager;
    private StudentManager studentManager;

    @BeforeEach
    void setUp() {
        criteriaManager = new CriteriaManager();
        evaluationManager = new EvaluationManager();
        studentManager = new StudentManager();
    }

    @Test
    void testAddCriteria() {
        String criteriaSubject = "Math";
        String criteriaEvaluation = "Final Exam";
        String criteriaType = "Written";
        String criteriaValue = "80%";

        criteriaManager.addCriteria(criteriaSubject, criteriaEvaluation, criteriaType, criteriaValue);
        TreeMap<String, Criteria> criteriaMap = criteriaManager.getCriteriaMap();

        assertEquals(1, criteriaMap.size());
        Criteria criteria = criteriaMap.firstEntry().getValue();
        assertEquals(criteriaSubject, criteria.Get_Criteria_Subject());
        assertEquals(criteriaEvaluation, criteria.Get_Criteria_Evaluation());
        assertEquals(criteriaType, criteria.Get_Criteria_Type());
        assertEquals(criteriaValue, criteria.Get_Criteria_Value());
    }

    @Test
    void testGetCriteriaMap() {
        assertTrue(criteriaManager.getCriteriaMap().isEmpty());

        criteriaManager.addCriteria("Math", "Final Exam", "Written", "80%");

        assertFalse(criteriaManager.getCriteriaMap().isEmpty());
    }

    @Test
    void testAddEvaluation() {
        String subjectName = "Math";
        String evaluationName = "Final Exam";
        String studentName = "John Doe";
        String exerciseName = "Exercise 1";
        double grade = 90.0;
        String evaluationType = "Written";

        evaluationManager.addEvaluation(subjectName, evaluationName, studentName, exerciseName, grade, evaluationType);
        Map<String, Evaluation> evaluations = evaluationManager.getEvaluations();

        assertEquals(1, evaluations.size());
        Evaluation evaluation = evaluations.get(subjectName + "-" + evaluationName + "-" + studentName + "-" + evaluationType);
        assertNotNull(evaluation);
        assertTrue(evaluation.Get_Grades().containsKey(exerciseName));
        assertEquals(grade, evaluation.Get_Grades().get(exerciseName));
    }

    @Test
    void testGetEvaluations() {
        assertTrue(evaluationManager.getEvaluations().isEmpty());

        evaluationManager.addEvaluation("Math", "Final Exam", "John Doe", "Exercise 1", 90.0, "Written");

        assertFalse(evaluationManager.getEvaluations().isEmpty());
    }

    @Test
    void testAddStudent() {
        String studentName = "John Doe";
        String courseName = "Math 101";

        studentManager.addStudent(studentName, courseName);
        TreeMap<String, Student> studentMap = studentManager.getStudentMap();

        assertEquals(1, studentMap.size());
        Student student = studentMap.get(studentName);
        assertNotNull(student);
        assertTrue(student.has_Already_Course(courseName));
        assertEquals(1, student.Get_Course_Count());
    }

    @Test
    void testAddMultipleCoursesForStudent() {
        String studentName = "John Doe";
        String courseName1 = "Math 101";
        String courseName2 = "History 101";

        studentManager.addStudent(studentName, courseName1);
        studentManager.addStudent(studentName, courseName2);

        Student student = studentManager.getStudentMap().get(studentName);

        assertTrue(student.has_Already_Course(courseName1));
        assertTrue(student.has_Already_Course(courseName2));
        assertEquals(2, student.Get_Course_Count());
    }

    @Test
    void testGetStudentMap() {
        assertTrue(studentManager.getStudentMap().isEmpty());

        studentManager.addStudent("John Doe", "Math 101");

        assertFalse(studentManager.getStudentMap().isEmpty());
    }
}
