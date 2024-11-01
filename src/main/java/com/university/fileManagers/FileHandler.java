package com.university.fileManagers;

import com.university.generators.Criteria;
import com.university.generators.Evaluation;
import com.university.generators.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.io.*;
import java.util.*;

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
}
