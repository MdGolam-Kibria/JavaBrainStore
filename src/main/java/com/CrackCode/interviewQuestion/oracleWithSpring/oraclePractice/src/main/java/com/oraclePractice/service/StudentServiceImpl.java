package com.oraclePractice.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createStudent() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addstudent");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        //set value
        query.setParameter(1, Long.parseLong("302"));
        query.setParameter(2, "spring boot");
        query.setParameter(3, "spring@gmail.com");
        query.setParameter(4, "01771598949");
        query.setParameter(5, "413152");
        //now execute the query

        query.execute();
    }
}
