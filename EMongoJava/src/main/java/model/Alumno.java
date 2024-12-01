package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Alumno implements Serializable {

    private String id;
    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private int calification;
    private String higher_grade;
    private boolean fct;


    public Alumno(double rating, int age, String name, String gender, String email, String phone, int calification, String higher_grade, boolean fct) {
        this.rating = rating;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.calification = calification;
        this.higher_grade = higher_grade;
        this.fct = fct;
    }
}
