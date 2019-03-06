package com.deepak.springbootrest.cond.conditions;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CondNotExists extends AbstractCond {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return ! extractPropCondition(context);
    }

}
