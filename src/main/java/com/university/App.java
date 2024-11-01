package com.university;

import com.university.FileManagers.FileHandler;
import com.university.Managers.EvaluationManager;
import com.university.Managers.StudentManager;
import com.university.Managers.CriteriaManager;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Input_2CSV = "src/main/resources/input_2.csv";
        String Input_3CSV = "src/main/resources/input_3.csv";
        String Output_CSV = "src/main/resources/student.csv";
        String Output_Evaluation_CSV = "src/main/resources/evaluation.csv";
        String Output_Criteria_CSV = "src/main/resources/criteria.csv";

        FileHandler fileHandler = new FileHandler();
        StudentManager studentManager = new StudentManager();
        EvaluationManager evaluationManager = new EvaluationManager();
        CriteriaManager criteriaManager = new CriteriaManager();

        try {
            List<String[]> data = fileHandler.readCSV(Input_CSV);
            List<String[]> data2 = fileHandler.readCSV(Input_2CSV);
            List<String[]> data3 = fileHandler.readCSV(Input_3CSV);

            for (String[] entry : data) {
                String studentName = entry[2];
                String courseCount = entry[1];
                studentManager.addStudent(studentName, courseCount);
            }
            for (String[] entry : data2) {
                String studentName = entry[0];
                String subjectName = entry[1];
                String evaluationType = entry[2];
                String evaluationName = entry[3];
                String exerciseName = entry[4];
                double grade = Double.parseDouble(entry[5]);
                evaluationManager.addEvaluation(subjectName, evaluationName, studentName, exerciseName, grade, evaluationType);
            }
            for (String[] entry : data3) {
                if (entry.length >= 4) {
                    String CriteriaSubject = entry[0];
                    String CriteriaEvaluation = "";
                    for (int i = 3; i < entry.length; i++) {
                        CriteriaEvaluation += entry[i];
                        if (i < entry.length - 1) {
                            CriteriaEvaluation += ", ";
                        }
                    }
                    String CriteriaType = entry[1];
                    String CriteriaValue = entry[2];
                    criteriaManager.addCriteria(CriteriaSubject, CriteriaEvaluation, CriteriaType, CriteriaValue);
                } else {
                    System.out.println("Skipping entry due to insufficient data: " + Arrays.toString(entry));
                }
            }
            fileHandler.writeCSV(Output_CSV, studentManager.getStudentMap());
            fileHandler.writeEvaluationCSV(Output_Evaluation_CSV, evaluationManager.getEvaluations());
            fileHandler.writeCriteriaCSV(Output_Criteria_CSV, criteriaManager.getCriteriaMap());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
