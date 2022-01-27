package com.CrackCode.database.INNER_JOIN.repository;

import com.CrackCode.database.INNER_JOIN.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
}
