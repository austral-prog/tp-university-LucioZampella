package com.university.generators;

public class Criteria implements Identifiable {
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

    @Override
    public String getId() {
        return CriteriaSubject + "," + CriteriaEvaluation;
    }

    public String Get_Criteria_Type() { return CriteriaType; }

    public String Get_Criteria_Value() { return CriteriaValue; }
}
