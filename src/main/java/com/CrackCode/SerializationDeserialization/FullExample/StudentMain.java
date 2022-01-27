package com.CrackCode.SerializationDeserialization.FullExample;

import java.io.*;

public class StudentMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student();
        student.setName("Golam Kibria");
        student.setAddress("Dhaka, Bangladesh");
        student.setRollNo(10);

        String fileLocation = "D:\\CrackCodeBd\\res\\student.ser";
        /**
         * For create file
         */
        FileOutputStream file = new FileOutputStream(fileLocation);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(student);
        out.close();
        file.close();

        System.out.println("Student Object serialized successfully");
        /**
         * For get Object Data from file
         */
        FileInputStream fileIn = new FileInputStream(fileLocation);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Student studentDeserializedObject = (Student) in.readObject(); // Object deserialized here.
        System.out.println("Name: " + studentDeserializedObject.getName());
        System.out.println("Address: " + studentDeserializedObject.getAddress());
        System.out.println("Roll: " + studentDeserializedObject.getRollNo());
        fileIn.close();
        in.close();
    }
}
