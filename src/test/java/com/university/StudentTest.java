package com.university;

import com.university.Generators.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentConstructorAndGetters() {
        Student student = new Student("Ronaldo", 5);
        assertEquals("Ronaldo", student.Get_Name());
        assertEquals(5, student.Get_Course_Count());
    }

    @Test
    public void testIncrementCourseCount() {
        Student student = new Student("Messi", 2);
        student.increment_Course_Count();
        assertEquals(3, student.Get_Course_Count());
    }

    @Test
    public void testAddCourse() {
        Student student = new Student("", 1);
        student.Add_Course("Maths");
        assertTrue(student.has_Already_Course("Maths"));
    }

    @Test
    public void testHasAlreadyCourse() {
        Student student = new Student("Neymar", 1);
        student.Add_Course("Science");
        assertTrue(student.has_Already_Course("Science"));
        assertFalse(student.has_Already_Course("History"));
    }

    @Test
    public void testGetCourses() {
        Student student = new Student("El changuito zeballos", 1);
        student.Add_Course("Geography");
        student.Add_Course("History");
        assertTrue(student.getCourses().contains("Geography"));
        assertTrue(student.getCourses().contains("History"));
        assertEquals(2, student.getCourses().size());
    }
}
