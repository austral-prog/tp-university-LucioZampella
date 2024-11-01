
package com.university;

import com.university.generators.Evaluation;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

    public class EvaluationTest {

        @Test
        public void testEvaluationConstructorAndGetters() {
            Evaluation evaluation = new Evaluation("Mathematics", "Exam 1", "Exercise 1", "Test");
            assertEquals("Mathematics", evaluation.Get_Subject_Name());
            assertEquals("Exam 1", evaluation.Get_Evaluation_Name());
            assertEquals("Exercise 1", evaluation.Get_ExerciseName());
            assertEquals("Test", evaluation.Get_Evaluation_Type());
            assertTrue(evaluation.Get_Grades().isEmpty());
        }

        @Test
        public void testAddGrade() {
            Evaluation evaluation = new Evaluation("History", "Exam 2", "Exercise 1", "Assignment");
            evaluation.Add_Grade("Exercise 1", 8.5);
            Map<String, Double> grades = evaluation.Get_Grades();
            assertEquals(1, grades.size());
            assertEquals(8.5, grades.get("Exercise 1"));
        }

        @Test
        public void testCalculateFinalGrade() {
            Evaluation evaluation = new Evaluation("Physics", "practicalwork", "Problem 1", "Exam");
            evaluation.Add_Grade("Problem 1", 7.0);
            evaluation.Add_Grade("Problem 2", 9.0);
            evaluation.Add_Grade("Problem 3", 8.0);
            double finalGrade = evaluation.calculateFinalGrade();
            assertEquals(8.0, finalGrade, 0.01);
        }
    }
