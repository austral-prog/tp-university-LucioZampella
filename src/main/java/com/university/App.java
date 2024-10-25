package com.university;

import com.university.FileManagers.FileHandler;
import com.university.Managers.StudentManager;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Input_2CSV = "src/main/resources/input_2.csv";
        String Output_CSV = "src/main/resources/student.csv";
        String Output_Evaluation_CSV = "src/main/resources/evaluation.csv";

        FileHandler fileHandler = new FileHandler();
        StudentManager studentManager = new StudentManager();

        try {
            List<String[]> data = fileHandler.readCSV(Input_CSV);
            List<String[]> data2 = fileHandler.readCSV(Input_2CSV);

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
                studentManager.addEvaluation(subjectName, evaluationName, studentName, exerciseName, grade, evaluationType);
            }
            fileHandler.writeCSV(Output_CSV, studentManager.getStudentMap());
            fileHandler.writeEvaluationCSV(Output_Evaluation_CSV, studentManager.getEvaluations());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
