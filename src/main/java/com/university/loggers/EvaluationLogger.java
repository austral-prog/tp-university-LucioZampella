package com.university.loggers;

import com.university.fileManagers.EvaluationWriter;
import com.university.fileManagers.FileHandler;
import com.university.managers.EvaluationManager;
import com.university.managers.StudentManager;

import java.io.IOException;
import java.util.List;

public class EvaluationLogger {
    public static void main(String[] args) {
        String Input_2CSV = "src/main/resources/input_2.csv";
        String Output_Evaluation_CSV = "src/main/resources/evaluation.csv";

        FileHandler fileHandler = new FileHandler();
        EvaluationWriter evaluationWriter = new EvaluationWriter();
        EvaluationManager evaluationManager = new EvaluationManager();

        try {
            List<String[]> data2 = fileHandler.readCSV(Input_2CSV);

            for (String[] entry : data2) {

                String studentName = entry[0];
                String subjectName = entry[1];
                String evaluationType = entry[2];
                String evaluationName = entry[3];
                String exerciseName = entry[4];
                double grade = Double.parseDouble(entry[5]);
                evaluationManager.addEvaluation(subjectName, evaluationName, studentName, exerciseName, grade, evaluationType);
            }

            evaluationWriter.writeCSV(Output_Evaluation_CSV, evaluationManager.getEvaluations());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
