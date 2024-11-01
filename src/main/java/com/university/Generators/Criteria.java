package com.university.Generators;

public class Criteria {
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

    public String Get_Criteria_Type() {  return CriteriaType; }

    public String Get_Criteria_Value() { return CriteriaValue; }

    public String Get_Criteria_Name() { return CriteriaEvaluation; }

    public String Get_Criteria_Evaluation() { return CriteriaEvaluation; }
}
