package com.amplifyframework.testmodels.parenting;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Parent type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Parents")
public final class Parent implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField ADDRESS = field("address");
  public static final QueryField CHILDREN = field("children");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Address", isRequired = true) Address address;
  private final @ModelField(targetType="Child") List<Child> children;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Address getAddress() {
      return address;
  }
  
  public List<Child> getChildren() {
      return children;
  }
  
  private Parent(String id, String name, Address address, List<Child> children) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.children = children;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Parent parent = (Parent) obj;
      return ObjectsCompat.equals(getId(), parent.getId()) &&
              ObjectsCompat.equals(getName(), parent.getName()) &&
              ObjectsCompat.equals(getAddress(), parent.getAddress()) &&
              ObjectsCompat.equals(getChildren(), parent.getChildren());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getAddress())
      .append(getChildren())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Parent {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("children=" + String.valueOf(getChildren()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Parent justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Parent(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      address,
      children);
  }
  public interface NameStep {
    AddressStep name(String name);
  }
  

  public interface AddressStep {
    BuildStep address(Address address);
  }
  

  public interface BuildStep {
    Parent build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep children(List<Child> children);
  }
  

  public static class Builder implements NameStep, AddressStep, BuildStep {
    private String id;
    private String name;
    private Address address;
    private List<Child> children;
    @Override
     public Parent build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Parent(
          id,
          name,
          address,
          children);
    }
    
    @Override
     public AddressStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep address(Address address) {
        Objects.requireNonNull(address);
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep children(List<Child> children) {
        this.children = children;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, Address address, List<Child> children) {
      super.id(id);
      super.name(name)
        .address(address)
        .children(children);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder address(Address address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder children(List<Child> children) {
      return (CopyOfBuilder) super.children(children);
    }
  }
  
}
