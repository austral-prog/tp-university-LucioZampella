package com.university.generators;

import com.university.CLI.Entity;

import java.util.HashMap;
import java.util.Map;

public class Evaluation implements Entity {
    private String Subject_Name;
    private String Evaluation_Name;
    private Map<String, Double> studentGrades;
    private String exerciseName;
    private String evaluationType;

    public Evaluation(String subjectName, String evaluationName, String exerciseName, String evaluationType) {
        this.Subject_Name = subjectName;
        this.Evaluation_Name = evaluationName;
        this.exerciseName = exerciseName;
        this.studentGrades = new HashMap<>();
        this.evaluationType = evaluationType;
    }

    public String getSubjectName() { return Subject_Name; }

    public String getEvaluationName() { return Evaluation_Name; }

    public void Add_Grade(String exerciseName, double grade) {
        studentGrades.put(exerciseName, grade);
    }

    public Map<String, Double> Get_Grades() {
        return studentGrades;
    }

    public String Get_ExerciseName() {
        return exerciseName;
    }

    public String Get_Evaluation_Type() {
        return evaluationType;
    }

    public double calculateFinalGrade() {
        double sum = 0.0;
        for (Double grade : studentGrades.values()) {
            sum += grade;
        }
        return sum / studentGrades.size();
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
