package com.university.fileManagers;

import com.university.generators.Criteria;

import java.io.IOException;
import java.util.Map;

public class CriteriaWriter extends Writer<Criteria> {

    @Override
    protected String getHeader() {
        return "Subject_Name,Evaluation_Name,Criteria_Type,Criteria_Value";
    }

    @Override
    protected String formatData(Criteria criteria) {
        return criteria.getId() + "," + criteria.Get_Criteria_Type() + "," +
                criteria.Get_Criteria_Value();
    }
}
