package com.deepak.springbootrest.cond.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;

public abstract class AbstractCond implements Condition {

    protected Boolean extractPropCondition(ConditionContext context) {
        Boolean someProp = context.getEnvironment().getProperty("someProp", Boolean.class);
        return someProp;
    }
}
