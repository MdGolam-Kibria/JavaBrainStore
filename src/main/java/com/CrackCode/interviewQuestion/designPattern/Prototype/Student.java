package com.CrackCode.interviewQuestion.designPattern.Prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student implements ObjectBridge {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String address;

    @Override
    public ObjectBridge copy() {
        return this;
    }
}
