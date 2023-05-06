package com.example.demo.authors.config;

import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GraphQLFederationConfig {

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return builder -> {
            builder.schemaFactory((registry, wiring)->
                    Federation.transform(registry, wiring)
                            .fetchEntities(env -> null)
                            .resolveEntityType(env -> null)
                            .build()
            );
        };
    }
}
