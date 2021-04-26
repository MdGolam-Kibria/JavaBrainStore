package com.CrackCode.interviewQuestion.designPattern.Prototype;

public class PrototypeResult {
    public static void main(String[] args) {
        Student student =
                new Student(
                        Long.parseLong("1"), "golam kibria", "golamkibria.java@gmail.com",
                        "01531921892", "413152413152", "student", "dhanmondi dhaka"
                );

        Student studentClone = (Student) student.copy();
        /**
         * After copy the object print the object value
         */
        System.out.println(studentClone);

        System.out.println("\n \n");
        /**
         * Now for our business logic we need another object like this just need to
         * change only one property of the object what can i do that ???? show below
         */
        Student student2 = (Student) student.copy();
        student2.setAddress("Naogaon");
        System.out.println(student2);

    }
}
