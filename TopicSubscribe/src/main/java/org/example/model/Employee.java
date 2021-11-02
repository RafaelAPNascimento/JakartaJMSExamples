package org.example.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employee implements Serializable {

    private String name;
    private int age;
    private double salary;
    private boolean gender;
}
