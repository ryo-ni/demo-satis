package com.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.web.entity.TestResultEntity;


@Repository
public interface TestResultRepository extends JpaRepository<TestResultEntity, Integer> {

}
