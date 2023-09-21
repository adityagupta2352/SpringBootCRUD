package com.springboot.SpringBootRestApi.repository;

import com.springboot.SpringBootRestApi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company , Long> {
}
