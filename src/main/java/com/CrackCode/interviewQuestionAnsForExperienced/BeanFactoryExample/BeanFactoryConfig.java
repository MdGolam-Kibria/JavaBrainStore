package com.CrackCode.interviewQuestionAnsForExperienced.BeanFactoryExample;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactoryConfig {

    @Bean
    public MyBean myBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyBean.class);//@INFO set bean class name here.

        /** @INFO below "beanFactoryExampleBean" is the identifier or name assigned to the bean definition within
        the Spring IoC container. This name is used to uniquely identify and retrieve the bean from
        the IOC container when needed.
         */
        beanFactory.registerBeanDefinition("beanFactoryExampleBean", beanDefinition);
        return beanFactory.getBean(MyBean.class);
    }
}