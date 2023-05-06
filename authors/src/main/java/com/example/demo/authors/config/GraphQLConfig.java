package com.example.demo.authors.config;

import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GraphQLConfig {
//    @Bean
//    @ConditionalOnProperty( prefix = "graphql.tracing", name = "enabled", matchIfMissing = true)
//    public Instrumentation tracingInstrumentation(){
//        return new TracingInstrumentation();
//    }

}
