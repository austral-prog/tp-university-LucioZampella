package com.university.loggers;

import com.university.fileManagers.CriteriaWriter;
import com.university.fileManagers.FileHandler;
import com.university.managers.CriteriaManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CriteriaLogger {
    public static void main(String[] args) {
        String Input_3CSV = "src/main/resources/input_3.csv";
        String Output_Criteria_CSV = "src/main/resources/criteria.csv";

        FileHandler fileHandler = new FileHandler();
        CriteriaWriter criteriaWriter = new CriteriaWriter();
        CriteriaManager criteriaManager = new CriteriaManager();

        try {
            List<String[]> data3 = fileHandler.readCSV(Input_3CSV);

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

            criteriaWriter.writeCriteriaCSV(Output_Criteria_CSV, criteriaManager.getCriteriaMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
