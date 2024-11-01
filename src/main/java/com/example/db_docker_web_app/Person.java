package com.example.db_docker_web_app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("country")
    String country;

    // Default constructor
    public Person() {
    }

    public Person(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }
}
