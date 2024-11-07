package com.university.fileManagers;

import com.university.generators.Evaluation;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class EvaluationWriter extends Writer<Evaluation> {

    @Override
    protected String getHeader() {
        return "Subject_Name,Evaluation_Name,Student_Name,Final_Grade";
    }

    @Override
    protected String formatData(Evaluation evaluation) {
        String studentName = evaluation.Get_Grades().keySet().iterator().next();
        return evaluation.getSubjectName() + "," + evaluation.getEvaluationName() + "," +  studentName + "," + String.format(Locale.US, "%.1f", evaluation.calculateFinalGrade());
    }
}
