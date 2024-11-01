package com.university.fileManagers;

import com.university.generators.Student;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class StudentWriter extends FileHandler {
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
}
