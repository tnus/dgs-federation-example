 /** Generated by the default template from graphql-java-generator */
package com.example.demo.authors.graphql.generated;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLScalar;

import com.graphql_java_generator.annotation.GraphQLDirective;

/**
 *
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("Author")
@JsonInclude(Include.NON_NULL)
@SuppressWarnings("unused")
public class Author
{
 

	/**
	 * This map contains the deserialized values for the alias, as parsed from the json response from the GraphQL
	 * server. The key is the alias name, the value is the deserialiazed value (taking into account custom scalars,
	 * lists, ...)
	 */
	@com.graphql_java_generator.annotation.GraphQLIgnore
	Map<String, Object> aliasValues = new HashMap<>();

	public Author(){
		// No action
	}

	@JsonProperty("name")
	@GraphQLScalar( fieldName = "name", graphQLTypeSimpleName = "String", javaClass = java.lang.String.class, listDepth = 0)
	java.lang.String name;


	@JsonProperty("__typename")
	@GraphQLScalar( fieldName = "__typename", graphQLTypeSimpleName = "String", javaClass = java.lang.String.class, listDepth = 0)
	java.lang.String __typename;



	@JsonProperty("name")
	public void setName(java.lang.String name) {
		this.name = name;
	}

	@JsonProperty("name")
	public java.lang.String getName() {
		return name;
	}
		

	@JsonProperty("__typename")
	public void set__typename(java.lang.String __typename) {
		this.__typename = __typename;
	}

	@JsonProperty("__typename")
	public java.lang.String get__typename() {
		return __typename;
	}
		

 
	/**
	 * This method is called during the json deserialization process, by the {@link GraphQLObjectMapper}, each time an
	 * alias value is read from the json.
	 * 
	 * @param aliasName
	 * @param aliasDeserializedValue
	 */
	public void setAliasValue(String aliasName, Object aliasDeserializedValue) {
		aliasValues.put(aliasName, aliasDeserializedValue);
	}

	/**
	 * Retrieves the value for the given alias, as it has been received for this object in the GraphQL response. <BR/>
	 * This method <B>should not be used for Custom Scalars</B>, as the parser doesn't know if this alias is a custom
	 * scalar, and which custom scalar to use at deserialization time. In most case, a value will then be provided by
	 * this method with a basis json deserialization, but this value won't be the proper custom scalar value.
	 * 
	 * @param alias
	 * @return
	 */
	public Object getAliasValue(String alias) {
		return aliasValues.get(alias);
	}

    public String toString() {
        return "Author {"
				+ "name: " + name
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * The Builder that helps building instance of this POJO. You can get an instance of this class, by calling the
	 * {@link #builder()}
	 */
	public static class Builder {
		private java.lang.String name;

		public Builder withName(java.lang.String name) {
			this.name = name;
			return this;
		}



		public Author build() {
			Author _object = new Author();
			_object.setName(name);
			_object.set__typename("Author");
			return _object;
		}
	}
}
