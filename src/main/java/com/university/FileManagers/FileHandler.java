package com.university.FileManagers;

import com.university.Generators.Criteria;
import com.university.Generators.Evaluation;
import com.university.Generators.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
    public List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                data.add(line.split(","));
            }
        }
        return data;
    }

    public void writeCSV(String filePath, TreeMap<String, Student> studentMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Student_Name,Course_Count");
            bw.newLine();
            for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
                String studentName = entry.getKey();
                int courseCount = entry.getValue().Get_Course_Count();
                bw.write(studentName + "," + courseCount);
                bw.newLine();
            }
        }
    }

    public void writeEvaluationCSV(String filePath, Map<String, Evaluation> evaluationMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Subject_Name,Evaluation_Name,Student_Name,Final_Grade");
            bw.newLine();
            for (Map.Entry<String, Evaluation> entry : evaluationMap.entrySet()) {
                Evaluation evaluation = entry.getValue();
                double finalGrade = evaluation.calculateFinalGrade();
                bw.write(evaluation.Get_Subject_Name() + "," + evaluation.Get_Evaluation_Name() + "," + entry.getKey().split("-")[2] + "," + String.format("%.1f", finalGrade));
                bw.newLine();
            }
        }
    }

    public void writeCriteriaCSV(String filePath, TreeMap<String, Criteria> criteriaMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Subject_Name,Evaluation_Name,Criteria_Type,Criteria_Value");
            bw.newLine();
            for (Map.Entry<String, Criteria> entry : criteriaMap.entrySet()) {
                String subjectName = entry.getKey();
                Criteria criteria = entry.getValue();
                bw.write(subjectName);
                bw.newLine();
            }
        }
    }
}
