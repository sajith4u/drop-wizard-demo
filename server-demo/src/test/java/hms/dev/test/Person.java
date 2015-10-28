package hms.dev.test;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sajith on 10/28/15.
 */
public class Person {
    private String name;
    private String email;

    private Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }
}
