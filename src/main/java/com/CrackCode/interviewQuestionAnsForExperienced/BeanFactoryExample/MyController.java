package com.CrackCode.interviewQuestionAnsForExperienced.BeanFactoryExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final ServiceClass serviceClass;

    public MyController(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    @GetMapping("/testBeanFactory")
    public String invokeService() {
        serviceClass.useMyBean();
        return "Service invoked!";
    }
}