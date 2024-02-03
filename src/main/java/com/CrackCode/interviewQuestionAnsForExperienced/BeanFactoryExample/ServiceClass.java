package com.CrackCode.interviewQuestionAnsForExperienced.BeanFactoryExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {


    private final MyBean myBean;

    public ServiceClass(MyBean myBean) {
        this.myBean = myBean;
    }


    public void useMyBean() {
        myBean.doSomething();
    }
}
