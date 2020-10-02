/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.testmodels.personcar;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.Objects;
import java.util.UUID;

/**
 * This is an autogenerated class representing the Person type in your schema.
 */
@SuppressWarnings("all")
@ModelConfig
@Index(fields = {"first_name", "age"}, name = "first_name_and_age_based_index")
public final class Person implements Model {
    // Constant QueryFields for each property in this model to be used for constructing conditions
    public static final QueryField ID = QueryField.field("id");
    public static final QueryField FIRST_NAME = QueryField.field("first_name");
    public static final QueryField LAST_NAME = QueryField.field("last_name");
    public static final QueryField AGE = QueryField.field("age");
    public static final QueryField DOB = QueryField.field("dob");
    public static final QueryField RELATIONSHIP = QueryField.field("relationship");

    @ModelField(targetType = "ID", isRequired = true)
    private final String id;

    @ModelField(isRequired = true)
    private final String first_name;

    @ModelField(isRequired = true)
    private final String last_name;

    @ModelField(targetType = "Int")
    private final Integer age;

    @ModelField(targetType = "AWSDate")
    private final Temporal.Date dob;

    @ModelField
    private final MaritalStatus relationship;

    private Person(String id,
                   String first_name,
                   String last_name,
                   Integer age,
                   Temporal.Date dob,
                   MaritalStatus relationship) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.dob = dob;
        this.relationship = relationship;
    }

    /**
     * Returns an instance of the builder at the first required step.
     * @return an instance of the builder.
     */
    public static FirstNameStep builder() {
        return new Builder();
    }

    /**
     * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
     *
     * This is a convenience method to return an instance of the object with only its ID populated
     * to be used in the context of a parameter in a delete mutation or referencing a foreign key
     * in a relationship.
     * @param id the id of the existing item this instance will represent
     * @return an instance of this model with only ID populated
     */
    public static Person justId(String id) {
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
            throw new IllegalArgumentException(
                    "Model IDs must be unique in the format of UUID. This method is for creating instances " +
                    "of an existing object with only its ID field for sending as a mutation parameter. When " +
                    "creating a new object, use the standard builder method and leave the ID field blank."
            );
        }

        return new Person(
                id,
                null,
                null,
                null,
                null,
                null
        );
    }

    /**
     * Returns an instance of the pre-set builder to update values with.
     * @return an instance of the pre-set builder to update values with.
     */
    public NewBuilder newBuilder() {
        return new NewBuilder(id,
                first_name,
                last_name,
                age,
                dob,
                relationship);
    }

    /**
     * Returns the person's id.
     * @return The person's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the person's first name.
     * @return The person's first name.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Returns the person's last name.
     * @return The person's last name.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * Returns the person's age.
     * @return The person's age.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Returns the person's date of birth.
     * @return date of birth.
     */
    public Temporal.Date getDob() {
        return dob;
    }

    /**
     * Returns the person's relationship status.
     * @return relationship status.
     */
    public MaritalStatus getRelationship() {
        return relationship;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if(obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            Person person = (Person) obj;
            return ObjectsCompat.equals(getId(), person.getId()) &&
                    ObjectsCompat.equals(getAge(), person.getAge()) &&
                    ObjectsCompat.equals(getDob(), person.getDob()) &&
                    ObjectsCompat.equals(getFirstName(), person.getFirstName()) &&
                    ObjectsCompat.equals(getLastName(), person.getLastName()) &&
                    ObjectsCompat.equals(getRelationship(), person.getRelationship());
        }
    }

    @Override
    public int hashCode() {
        return new StringBuilder()
                .append(getId())
                .append(getDob())
                .append(getAge())
                .append(getFirstName())
                .append(getLastName())
                .append(getRelationship())
                .toString()
                .hashCode();
    }

    /**
     * Interface for required firstName step.
     */
    public interface FirstNameStep {
        /**
         * Set the person's first name.
         * @param firstName The person's first name.
         * @return next step.
         */
        LastNameStep firstName(String firstName);
    }

    /**
     * Interface for lastName step.
     */
    public interface LastNameStep {
        /**
         * Set the person's last name.
         * @param lastName The person's last name.
         * @return next step.
         */
        FinalStep lastName(String lastName);
    }

    /**
     * Interface for final step.
     */
    public interface FinalStep {
        /**
         * Set the person's id.
         * @param id The person's id.
         * @return next step.
         * @throws IllegalArgumentException Checks that ID is in the proper format
         */
        FinalStep id(String id) throws IllegalArgumentException;

        /**
         * Set the person's age.
         * @param age The person's age.
         * @return next step.
         */
        FinalStep age(Integer age);

        /**
         * Set the person's date of birth.
         * @param dob The person's date of birth.
         * @return next step.
         */
        FinalStep dob(Temporal.Date dob);

        /**
         * Set the person's relationship status.
         * @param relationship The person's relationship.
         * @return next step.
         */
        FinalStep relationship(MaritalStatus relationship);

        /**
         * Returns the built Person object.
         * @return the built Person object.
         */
        Person build();
    }

    /**
     * Builder to build the Person object.
     */
    private static class Builder implements
            FirstNameStep, LastNameStep, FinalStep {
        private String id;
        private String first_name;
        private String last_name;
        private Integer age;
        private Temporal.Date dob;
        private MaritalStatus relationship;

        /**
         * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
         *          This should only be set when referring to an already existing object.
         * @param id id
         * @return Current Builder instance, for fluent method chaining
         * @throws IllegalArgumentException Checks that ID is in the proper format
         */
        public FinalStep id(String id) throws IllegalArgumentException {
            Objects.requireNonNull(id);
            this.id = id;

            try {
                UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
            } catch (Exception exception) {
                throw new IllegalArgumentException("Model IDs must be unique in the format of UUID." +
                    "If you are creating a new object, leave ID blank and one will be autogenerated for you. " +
                    "Otherwise, if you are referencing an existing object, be sure you are getting the correct " +
                    "id for it. It's also possible you are referring to an item created outside of Amplify. We " +
                    "currently do not support this."
                );
            }

            return this;
        }

        /**
         * Sets the person's first name.
         * @param firstName The person's first name.
         * @return Current Builder instance, for fluent method chaining
         */
        public LastNameStep firstName(String first_name) {
            Objects.requireNonNull(first_name);
            this.first_name = first_name;
            return this;
        }

        /**
         * Sets the person's last name.
         * @param lastName The person's last name.
         * @return Current Builder instance, for fluent method chaining
         */
        public FinalStep lastName(String last_name) {
            Objects.requireNonNull(last_name);
            this.last_name = last_name;
            return this;
        }

        /**
         * Sets the person's age.
         * @param age The person's age
         * @return Current Builder instance, for fluent method chaining
         */
        public FinalStep age(Integer age) {
            this.age = age;
            return this;
        }

        /**
         * Sets the person's date of birth.
         * @param dob The person's date of birth.
         * @return Current Builder instance, for fluent method chaining
         */
        public FinalStep dob(Temporal.Date dob) {
            this.dob = dob == null ? null : new Temporal.Date(dob.toDate());
            return this;
        }

        /**
         * Sets the person's relationship status.
         * @param relationship The person's relationship status.
         * @return Current Builder instance, for fluent method chaining
         */
        public FinalStep relationship(MaritalStatus relationship) {
            this.relationship = relationship;
            return this;
        }

        /**
         * Returns the builder object.
         * @return the builder object.
         */
        public Person build() {
            String id = this.id != null ? this.id : UUID.randomUUID().toString();

            return new Person(
                    id,
                    first_name,
                    last_name,
                    age,
                    dob,
                    relationship);
        }
    }

    /**
     * New Builder to update the Person object.
     */
    public final class NewBuilder extends Builder {
        private NewBuilder(String id,
                String first_name,
                String last_name,
                Integer age,
                Temporal.Date dob,
                MaritalStatus relationship) {
            super.id(id);
            super.firstName(first_name)
                    .lastName(last_name)
                    .age(age)
                    .dob(dob)
                    .relationship(relationship);
        }

        @Override
        public NewBuilder id(String id) throws IllegalArgumentException {
            return (NewBuilder) super.id(id);
        }

        @Override
        public NewBuilder firstName(String first_name) {
            return (NewBuilder) super.firstName(first_name);
        }

        @Override
        public NewBuilder lastName(String last_name) {
            return (NewBuilder) super.lastName(last_name);
        }

        @Override
        public NewBuilder age(Integer age) {
            return (NewBuilder) super.age(age);
        }

        @Override
        public NewBuilder dob(Temporal.Date dob) {
            return (NewBuilder) super.dob(dob);
        }

        @Override
        public NewBuilder relationship(MaritalStatus relationship) {
            return (NewBuilder) super.relationship(relationship);
        }
    }
}
