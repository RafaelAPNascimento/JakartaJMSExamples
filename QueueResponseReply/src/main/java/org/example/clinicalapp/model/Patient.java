package org.example.clinicalapp.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Patient implements Serializable {

    private String name;
    private int age;
    private double salary;
    private String position;
}
