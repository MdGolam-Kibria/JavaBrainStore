package com.CrackCode.IIT_DU;

import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private Integer roll;
    private Double cgpa;


    public Student() {
    }

    public Student(String name, Integer roll, Double cgpa) {
        this.name = name;
        this.roll = roll;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", roll=" + roll +
                ", cgpa=" + cgpa +
                '}';
    }

    private static List<Student> getStudentList() {
        Student student1 = new Student();
        student1.setName("kibria");
        student1.setRoll(11);
        student1.setCgpa(5.00);


        Student student2 = new Student();
        student2.setName("manik khan");
        student2.setRoll(12);
        student2.setCgpa(5.00);

        return Arrays.asList(student1, student2);
    }

    public static Student getStudentByRoll(Integer roll) {
        return getStudentList().stream()
                .filter(student -> student.getRoll().equals(roll))
                .findFirst().orElse(null);
    }

    public static void printAllStudentInfo() {
        getStudentList().forEach(student -> {
            System.out.println(student.toString());
        });
    }


    public static void main(String[] args) {
        printAllStudentInfo();
        Student expectedStudent = getStudentByRoll(11);
        if (expectedStudent == null) {
            System.out.println("Didn't find any student by the given ID");
            return;
        } else {
            System.out.println(expectedStudent.toString());
        }

    }
}
