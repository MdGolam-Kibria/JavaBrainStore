package com.CrackCode.database.INNER_JOIN.repository;

import com.CrackCode.database.SQL;
import com.CrackCode.database.INNER_JOIN.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    int countAllByIsActiveTrue();

    @Query(value = SQL.getChildNationalityByParentIdentity, nativeQuery = true)
    List<Map<String, Object>> getChildNationalityByMatchingParentChildId();//INNER JOIN


}
