package com.smashfinance.config.conditions;

import java.util.Arrays;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;

public class OnDevProfileCondition implements Condition {

    public boolean matches(@NonNull ConditionContext context,
            @NonNull AnnotatedTypeMetadata metadata) {
        return Arrays.asList(context.getEnvironment().getActiveProfiles()).contains("dev");
    }

}
