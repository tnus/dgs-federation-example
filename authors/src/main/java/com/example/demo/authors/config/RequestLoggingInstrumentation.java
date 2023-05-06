package com.example.demo.authors.config;

import graphql.ExecutionResult;
import graphql.execution.ExecutionId;
import graphql.execution.instrumentation.ExecutionStrategyInstrumentationContext;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionStrategyParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j

class RequestLoggingInstrumentation extends SimpleInstrumentation {
    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters, InstrumentationState state) {
        Instant begin = Instant.now();
        ExecutionId executionId = parameters.getExecutionInput().getExecutionId();

        log.info("{}: query: {} with variables: {}", executionId, parameters.getQuery(), parameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            Duration duration = Duration.between(begin, Instant.now());
            if (throwable == null) {
                log.info("{}: completed successfully in: {}", executionId, duration);
            } else {
                log.warn("{}: failed in: {}", executionId, duration, throwable);
            }
        });
    }

}
