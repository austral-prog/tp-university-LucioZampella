package com.university.loggers;

import com.university.fileManagers.FileHandler;
import com.university.fileManagers.StudentWriter;
import com.university.managers.StudentManager;

import java.io.IOException;
import java.util.List;

public class StudentLogger {
    public static void main(String[] args) {
        String Input_CSV = "src/main/resources/input.csv";
        String Output_CSV = "src/main/resources/student.csv";

        FileHandler fileHandler = new FileHandler();
        StudentWriter studentWriter = new StudentWriter();
        StudentManager studentManager = new StudentManager();

        try {
            List<String[]> data = fileHandler.readCSV(Input_CSV);

            for (String[] entry : data) {
                String studentName = entry[2];
                String courseCount = entry[1];
                studentManager.addStudent(studentName, courseCount);
            }
            studentWriter.writeCSV(Output_CSV, studentManager.getStudentMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
