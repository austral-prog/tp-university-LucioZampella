package com.university.fileManagers;


import com.university.generators.Criteria;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class CriteriaWriter {
    public void writeCriteriaCSV(String filePath, TreeMap<String, Criteria> criteriaMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Subject_Name,Evaluation_Name,Criteria_Type,Criteria_Value");
            bw.newLine();
            for (Map.Entry<String, Criteria> entry : criteriaMap.entrySet()) {
                String subjectName = entry.getKey();
                bw.write(subjectName);
                bw.newLine();
            }
        }
    }
}
