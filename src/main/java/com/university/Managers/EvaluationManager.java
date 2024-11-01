package com.university.Managers;

import com.university.Generators.Evaluation;

import java.util.Map;
import java.util.TreeMap;

public class EvaluationManager {
    private TreeMap<String, Evaluation> evaluations = new TreeMap<>();

    public void addEvaluation(String subjectName, String evaluationName, String studentName, String exerciseName, double grade, String evaluationType) {
        String key = subjectName + "-" + evaluationName + "-" + studentName + "-" + evaluationType;
        Evaluation evaluationInstance = evaluations.getOrDefault(key, new Evaluation(subjectName, evaluationName, exerciseName, evaluationType));
        evaluations.putIfAbsent(key, evaluationInstance);
        evaluationInstance.Add_Grade(exerciseName, grade);
    }

    public Map<String, Evaluation> getEvaluations() {
        return evaluations;
    }
}