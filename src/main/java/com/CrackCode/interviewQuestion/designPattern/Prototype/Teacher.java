package com.CrackCode.interviewQuestion.designPattern.Prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher implements ObjectBridge{
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
