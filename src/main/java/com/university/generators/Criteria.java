package com.university.generators;

import com.university.CLI.Entity;

public class Criteria implements Entity {
    private String CriteriaType;
    private String CriteriaValue;
    private String CriteriaEvaluation;
    private String CriteriaSubject;

    public Criteria(String CriteriaSubject, String CriteriaEvaluation, String CriteriaType, String CriteriaValue) {
        this.CriteriaType = CriteriaType;
        this.CriteriaEvaluation = CriteriaEvaluation;
        this.CriteriaValue = CriteriaValue;
        this.CriteriaSubject = CriteriaSubject;
    }

    public String Get_Criteria_Subject() { return CriteriaSubject; }

    public String Get_Criteria_Evaluation() { return CriteriaEvaluation; }

    public String Get_Criteria_Type() { return CriteriaType; }

    public String Get_Criteria_Value() { return CriteriaValue; }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
