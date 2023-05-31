package com.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.web.entity.TestRequestEntity;


@Repository
public interface TestRequestRepository extends JpaRepository<TestRequestEntity, Integer> {

}
