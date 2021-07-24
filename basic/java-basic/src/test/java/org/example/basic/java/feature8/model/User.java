package org.example.basic.java.feature8.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;

    public User(Long id) {
        this.id = id;
    }

    public User(String firstName) {
        this.firstName = firstName;
    }
}