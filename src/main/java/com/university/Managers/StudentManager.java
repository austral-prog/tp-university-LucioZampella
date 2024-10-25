package com.university.Managers;

import com.university.Generators.Evaluation;
import com.university.Generators.Student;

import java.util.Map;
import java.util.TreeMap;

public class StudentManager {
    private TreeMap<String, Student> studentMap = new TreeMap<>();
    private TreeMap<String, Evaluation> evaluations = new TreeMap<>();

    public void addStudent(String studentName, String courseName) {
        Student student;
        if (studentMap.containsKey(studentName)) {
            student = studentMap.get(studentName);
            if (!student.has_Already_Course(courseName)) {
                student.Add_Course(courseName);
                student.increment_Course_Count();
            }
        } else {
            student = new Student(studentName, 1);
            studentMap.put(studentName, student);
            student.Add_Course(courseName);
        }
    }

    public void addEvaluation(String subjectName, String evaluationName, String studentName, String exerciseName, double grade, String evaluationType) {
        String key = subjectName + "-" + evaluationName + "-" + studentName + "-" + evaluationType;
        Evaluation evaluationInstance = evaluations.getOrDefault(key, new Evaluation(subjectName, evaluationName, exerciseName, evaluationType));
        evaluations.putIfAbsent(key, evaluationInstance);
        evaluationInstance.Add_Grade(exerciseName, grade);
    }

    public TreeMap<String, Student> getStudentMap() {
        return studentMap;
    }

    public Map<String, Evaluation> getEvaluations() {
        return evaluations;
    }
}