 /** Generated by the default template from graphql-java-generator */
package com.example.demo.authors.graphql.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graphql_java_generator.annotation.GraphQLDirective;
import com.graphql_java_generator.annotation.GraphQLEnumType;

/**
 * 
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLEnumType("__DirectiveLocation")
@SuppressWarnings("unused")
public enum __DirectiveLocation {
	QUERY("QUERY"), 
	MUTATION("MUTATION"), 
	SUBSCRIPTION("SUBSCRIPTION"), 
	FIELD("FIELD"), 
	FRAGMENT_DEFINITION("FRAGMENT_DEFINITION"), 
	FRAGMENT_SPREAD("FRAGMENT_SPREAD"), 
	INLINE_FRAGMENT("INLINE_FRAGMENT"), 
	VARIABLE_DEFINITION("VARIABLE_DEFINITION"), 
	SCHEMA("SCHEMA"), 
	SCALAR("SCALAR"), 
	OBJECT("OBJECT"), 
	FIELD_DEFINITION("FIELD_DEFINITION"), 
	ARGUMENT_DEFINITION("ARGUMENT_DEFINITION"), 
	INTERFACE("INTERFACE"), 
	UNION("UNION"), 
	ENUM("ENUM"), 
	ENUM_VALUE("ENUM_VALUE"), 
	INPUT_OBJECT("INPUT_OBJECT"), 
	INPUT_FIELD_DEFINITION("INPUT_FIELD_DEFINITION");

	// The graphQlValue is needed on server side, to map the enum value to the value defined in the GraphQL schema. They are different
	// when the value in the GraphQL schema is a java reserved keyword.
	private final String graphQlValue;

	/**
	 * Returns the value of this constant, as specified in the GraphQL schema. This is usually the same as the enum
	 * item's name. But it will differ if this name is a java reserved keyword (in which case the name is prefixed by an
	 * underscore)
	 * 
	 * @return the enum constant with the specified name, as defined in the GraphQL schema
	 */
	public String graphQlValue() {
		return graphQlValue;
	}
	
	/**
	 * Returns the enum constant of this type with the specified name (as specified in the GraphQL schema). The string
	 * must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters
	 * are not permitted.)
	 * 
	 * @param graphQlValue
	 *            The value, as defined in the GraphQL schema. This is usually the same as the enum item's name. But it
	 *            will differ if this name is a java reserved keyword (in which case the name is prefixed by an
	 *            underscore)
	 * @return the enum constant with the specified name
	 * @throws IllegalArgumentException
	 *             if this enum type has no constant with the specified GraphQL name
	 */
	static public __DirectiveLocation fromGraphQlValue(String graphQlValue) {
		if (graphQlValue == null) {
			return null;
		}
		for (__DirectiveLocation e : __DirectiveLocation.values()) {
			if (e.graphQlValue().equals(graphQlValue)) {
				return e;
			}
		}
		throw new IllegalArgumentException("No __DirectiveLocation exists with '" + graphQlValue + "' as a GraphQL value");
	}

	__DirectiveLocation(String graphQlValue) {
		this.graphQlValue = graphQlValue;
	}
}
