package com.university.managers;

import com.university.generators.Criteria;

import java.util.TreeMap;

public class CriteriaManager {
    private TreeMap <String, Criteria> criteriaMap = new TreeMap <String, Criteria>();

    public void addCriteria(String CriteriaSubject, String CriteriaEvaluation, String CriteriaType, String CriteriaValue) {
        Criteria criteria;
        String key = CriteriaSubject + ", " + CriteriaEvaluation + ", " + CriteriaType + ", " + CriteriaValue;
        criteria = new Criteria(CriteriaSubject, CriteriaEvaluation, CriteriaType, CriteriaValue);
        criteriaMap.put(key, criteria);
    }

    public TreeMap <String, Criteria> getCriteriaMap() {return criteriaMap;}
}
