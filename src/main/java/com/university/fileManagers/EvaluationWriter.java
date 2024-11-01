package com.university.fileManagers;

import com.university.generators.Evaluation;

import java.io.*;
import java.util.Map;


public class EvaluationWriter extends FileHandler {
    public void writeEvaluationCSV(String filePath, Map<String, Evaluation> evaluationMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Subject_Name,Evaluation_Name,Student_Name,Final_Grade");
            bw.newLine();
            for (Map.Entry<String, Evaluation> entry : evaluationMap.entrySet()) {
                Evaluation evaluation = entry.getValue();
                double finalGrade = evaluation.calculateFinalGrade();
                bw.write(evaluation.Get_Subject_Name() + "," + evaluation.Get_Evaluation_Name() + ","
                        + entry.getKey().split("-")[2] + "," + String.format("%.1f", finalGrade));
                bw.newLine();
            }
        }
    }
}
