package com.example.demo.authors.config;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.example.demo.authors.graphql.generated.DgsConstants;
import com.example.demo.authors.graphql.generated.types.Show;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
class GraphQLFederationConfig {

//    @Bean
//    public GraphQlSourceBuilderCustomizer federationTransform() {
//        return builder -> {
//            builder.schemaFactory((registry, wiring)->
//                    Federation.transform(registry, wiring)
//                            .fetchEntities(env -> null)
//                            .resolveEntityType(env -> null)
//                            .build()
//            );
//        };
//    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        DataFetcher<?> entityDataFetcher = env -> {
            List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
            return representations.stream()
                    .map(representation -> {
                        log.info("map representation: {}", representation);

                        if (DgsConstants.SHOW.TYPE_NAME.equals(representation.get("__typename"))) {
                            return Show.newBuilder().id((String)representation.get("id")).build();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        };

        return builder ->
                builder.schemaFactory((registry, wiring)->
                        Federation.transform(registry, wiring)
                                .fetchEntities(entityDataFetcher)
                                .resolveEntityType(new ClassNameTypeResolver())
                                .build()
                );
    }
}
